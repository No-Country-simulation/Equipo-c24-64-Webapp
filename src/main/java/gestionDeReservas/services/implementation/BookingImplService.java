package gestionDeReservas.services.implementation;

import gestionDeReservas.exception.*;
import gestionDeReservas.factory.booking.BookingFactory;
import gestionDeReservas.factory.booking.BookingMailFactory;
import gestionDeReservas.mapper.RoomMapper;
import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.model.dto.booking.BookingMailDTO;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.*;
import gestionDeReservas.repository.IBookingRepository;
import gestionDeReservas.repository.IUserRepository;
import gestionDeReservas.repository.IVisitorRepository;
import gestionDeReservas.services.Interface.BookingMailService;
import gestionDeReservas.services.Interface.BookingService;
import gestionDeReservas.services.Interface.RoomTypeServiceUI;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingImplService implements BookingService {
    RoomTypeServiceUI roomTypeService;
    IBookingRepository bookingRepository;
    IVisitorRepository visitorRepository;
    IUserRepository userRepository;
    BookingMailService bookingMailService;
    BookingMailFactory bookingMailFactory;
    BookingFactory bookingFactory;
    RoomMapper roomMapper;

    @Override
    public void bookingRooms(BookingRequestDTO bookingRequestDTO) throws Exception {
        String email = bookingRequestDTO.email();
        Visitor visitor = visitorRepository.findByEmail(email).orElse(null);
        UserEntity user = userRepository.findByEmail(email).orElse(null);

        validateGuest(visitor,user);

        RoomType roomType = getRoomType(bookingRequestDTO.idRoomType());
        LocalDate checkIn = bookingRequestDTO.checkIn();
        LocalDate checkOut = bookingRequestDTO.checkOut();

        validateDates(checkIn, checkOut);

        List<Room> availableRooms = getAvailableRooms(roomType.getId(), checkIn, checkOut);

        int roomsRequested = bookingRequestDTO.roomsQuantity();
        validateQuantityRequestedRooms(availableRooms, roomsRequested);
        validateAvailableRooms(availableRooms);

        List<Room> bookingRooms = availableRooms.subList(0, roomsRequested);

        Booking booking = getBooking(bookingRequestDTO,user,visitor,bookingRooms);

        bookingRepository.save(booking);

        CreateBookingEmail(booking);
    }

    @Override
    public List<RoomGetDTO> getAvailableRoomsDTO(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut) throws Exception {
        return roomMapper.RoomGetAllDTO(getAvailableRooms(roomTypeId,checkIn,checkOut));
    }

    private List<Room> getAvailableRooms(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut) throws Exception {
        RoomType roomType = getRoomType(roomTypeId);
        return roomType.getRooms().stream()
                .filter(room ->  !isRoomBooked(room.getId(), checkIn, checkOut))
                .toList();
    }

    private RoomType getRoomType(Integer roomTypeId) throws Exception {
        return roomTypeService.getTypeById(roomTypeId);
    }

    private void CreateBookingEmail(Booking booking) {
        BookingMailDTO bookingMail = bookingMailFactory.buildBookingMail(booking);
        bookingMailService.sendBookingMail(bookingMail);
    }

    private Booking getBooking(BookingRequestDTO bookingRequestDTO,UserEntity user, Visitor visitor, List<Room> bookingRooms) {
        if(user == null)
            return bookingFactory.buildVisitorBooking(bookingRequestDTO,visitor,bookingRooms);

        return bookingFactory.buildBooking(bookingRequestDTO, user, bookingRooms);
    }

    private void validateGuest(Visitor visitor, UserEntity user) {
        if(visitor == null && user == null)
            throw new NotFoundException("guest not found");
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

    private void validateDates(LocalDate checkIn, LocalDate checkOut) {
        LocalDate currentDate = LocalDate.now();
        LocalDate validDate = checkIn.plusDays(1);
        if (checkOut.isBefore(validDate) || checkIn.isBefore(currentDate))
            throw new DateRangeException("The date range is invalid");
    }
}