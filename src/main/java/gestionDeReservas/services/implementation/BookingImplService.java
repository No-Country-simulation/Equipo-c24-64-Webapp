package gestionDeReservas.services.implementation;
import gestionDeReservas.exception.BookingException;
import gestionDeReservas.exception.DateRangeException;
import gestionDeReservas.exception.NotRoomFoundException;
import gestionDeReservas.factory.booking.BookingFactory;
import gestionDeReservas.factory.booking.BookingResponseFactory;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.dto.booking.BookingResponseDTO;
import gestionDeReservas.model.entity.Booking;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.RoomType;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.repository.IBookingRepository;
import gestionDeReservas.repository.IRoomTypeRepository;
import gestionDeReservas.services.Interface.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BookingImplService implements BookingService {
    IBookingRepository bookingRepository;
    IRoomTypeRepository roomTypeRepository;
    BookingFactory bookingFactory;
    BookingResponseFactory bookingResponseFactory;

    @Override
    public BookingResponseDTO saveBooking(UserEntity user, BookingRequestDTO bookingRequestDTO) {
        RoomType roomType = findRoomType(bookingRequestDTO.idRoomType());

        LocalDate checkIn = bookingRequestDTO.checkIn();
        LocalDate checkOut = bookingRequestDTO.checkOut();
        validateDates(checkIn,checkOut);

        List<Room> enableRooms = this.getEnableRooms(roomType.getId(),checkIn,checkOut);

        if(enableRooms.isEmpty())
            throw new BookingException("No reservations found for the requested dates");

        Booking booking = bookingFactory.buildBooking(bookingRequestDTO,user,enableRooms.get(0));
        bookingRepository.save(booking);

        return bookingResponseFactory.buildBookingResponse(booking.getTotalPrice(),checkIn,checkOut);
    }

    @Override
    public List<Room> getEnableRooms(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut) {
        List<Room> enabledRooms =  new ArrayList<>();
        RoomType roomType = findRoomType(roomTypeId);

        for(Room room: roomType.getRooms()){
            if(!isRoomBooked(room.getId(),checkIn,checkOut))
                enabledRooms.add(room);
        }
        return  enabledRooms;
    }

    private Boolean isRoomBooked(Integer idRoom, LocalDate checkIn, LocalDate checkOut) {
        return bookingRepository.countOverlappingReservations(idRoom,checkIn,checkOut);
    }

    private RoomType findRoomType(Integer idRoomType) {
        return roomTypeRepository.findById(idRoomType)
                .orElseThrow(() -> new NotRoomFoundException("not found room"));
    }

    private void validateDates(LocalDate checkIn, LocalDate checkOut) {
        if(checkOut.isBefore(checkIn))
            throw new DateRangeException("dates are invalid");
    }
}