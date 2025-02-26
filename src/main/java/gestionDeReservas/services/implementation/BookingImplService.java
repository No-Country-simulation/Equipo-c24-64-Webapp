package gestionDeReservas.services.implementation;

import gestionDeReservas.exception.BookingException;
import gestionDeReservas.exception.DateRangeException;
import gestionDeReservas.exception.NotRoomFoundException;
import gestionDeReservas.factory.booking.BookingFactory;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.Booking;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.RoomType;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.repository.IBookingRepository;
import gestionDeReservas.repository.IRoomTypeRepository;
import gestionDeReservas.services.Interface.BookingService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingImplService implements BookingService {

    IBookingRepository bookingRepository;
    IRoomTypeRepository roomTypeRepository;
    BookingFactory bookingFactory;

    @Override
    public void bookingRooms(UserEntity user, BookingRequestDTO bookingRequestDTO) {
        RoomType roomType = findRoomType(bookingRequestDTO.idRoomType());
        LocalDate checkIn = bookingRequestDTO.checkIn();
        LocalDate checkOut = bookingRequestDTO.checkOut();

        validateDates(checkIn, checkOut);

        List<Room> availableRooms = getAvailableRooms(roomType.getId(), checkIn, checkOut);

        int roomsRequested = bookingRequestDTO.roomsQuantity();
        validateQuantityRequestedRooms(availableRooms, roomsRequested);
        validateAvailableRooms(availableRooms);

        List<Room> bookingRooms = availableRooms.subList(0, roomsRequested);

        Booking booking = bookingFactory.buildBooking(bookingRequestDTO, user, bookingRooms);
        bookingRepository.save(booking);
    }

    @Override
    public List<Room> getAvailableRooms(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut) {
        RoomType roomType = findRoomType(roomTypeId);
        return roomType.getRooms().stream()
                .filter(room -> !isRoomBooked(room.getId(), checkIn, checkOut))
                .toList();
    }

    private void validateQuantityRequestedRooms(List<Room> availableRooms, int roomsRequested) {
        if (availableRooms.size() < roomsRequested)
            throw new BookingException("There are not enough rooms available for booking");
    }

    private void validateAvailableRooms(List<Room> availableRooms) {
        if (availableRooms.isEmpty())
            throw new BookingException("No available rooms found for the requested dates");
    }

    private boolean isRoomBooked(Integer roomId, LocalDate checkIn, LocalDate checkOut) {
        return bookingRepository.countOverlappingReservations(roomId, checkIn, checkOut);
    }

    private RoomType findRoomType(Integer idRoomType) {
        return roomTypeRepository.findById(idRoomType)
                .orElseThrow(() -> new NotRoomFoundException("Room type not found"));
    }

    private void validateDates(LocalDate checkIn, LocalDate checkOut) {
        if (checkOut.isBefore(checkIn))
            throw new DateRangeException("The date range is invalid");
    }
}