package gestionDeReservas.services.implementation;

import gestionDeReservas.exception.*;
import gestionDeReservas.factory.booking.BookingFactory;
import gestionDeReservas.factory.booking.BookingMailFactory;
import gestionDeReservas.mapper.BookingMapper;
import gestionDeReservas.model.dto.booking.BookingMailDTO;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.dto.booking.BookingResponseDTO;
import gestionDeReservas.model.entity.*;
import gestionDeReservas.repository.IBookingRepository;
import gestionDeReservas.services.Interface.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingImplService implements BookingService {
    IBookingRepository bookingRepository;
    RoomTypeServiceUI roomTypeService;
    RoomServiceUI roomService;
    VisitorService visitorService;
    AuthService authService;
    BookingMailService bookingMailService;
    BookingMailFactory bookingMailFactory;
    BookingFactory bookingFactory;
    BookingMapper bookingMapper;

    @SneakyThrows
    @Override
    public void bookingRooms(BookingRequestDTO bookingRequestDTO) {
        RoomType roomType = roomTypeService.getRoomTypeById(bookingRequestDTO.idRoomType());
        String email = bookingRequestDTO.email();
        Visitor visitor = visitorService.getVisitorByEmail(email);
        User user = authService.getUserByEmail(email);
        LocalDate checkIn = bookingRequestDTO.checkIn();
        LocalDate checkOut = bookingRequestDTO.checkOut();
        int roomsRequested = bookingRequestDTO.roomsQuantity();

        validateGuest(visitor,user);
        validateDates(checkIn, checkOut);

        List<Room> availableRooms = roomService.getAvailableRooms(roomType.getId(),
                checkIn, checkOut);

        validateQuantityRequestedRooms(availableRooms, roomsRequested);
        validateAvailableRooms(availableRooms);

        List<Room> bookingRooms = getBookingsRooms(availableRooms,roomsRequested);
        Booking booking = getBooking(bookingRequestDTO,user,visitor,bookingRooms);

        bookingRepository.save(booking);

        CreateBookingEmail(booking);
    }

    @Override
    public List<BookingResponseDTO> getBookingsFromuser(String email) {
        return  bookingMapper.BookingGetAllDTO(bookingRepository.findBookingsFromUser(email));
    }

    private void CreateBookingEmail(Booking booking) {
        BookingMailDTO bookingMail = bookingMailFactory.buildBookingMail(booking);
        bookingMailService.sendBookingMail(bookingMail);
    }

    private Booking getBooking(BookingRequestDTO bookingRequestDTO, User user,
                               Visitor visitor, List<Room> bookingRooms) {
        return bookingFactory.buildBooking(bookingRequestDTO, user,visitor, bookingRooms);
    }

    private void validateGuest(Visitor visitor, User user) {
        if (visitor == null && user == null)
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

    private void validateDates(LocalDate checkIn, LocalDate checkOut) {
        LocalDate currentDate = LocalDate.now();
        LocalDate validDate = checkIn.plusDays(1);
        if (checkOut.isBefore(validDate) || checkIn.isBefore(currentDate))
            throw new DateRangeException("The date range is invalid");
    }

    private List<Room> getBookingsRooms(List<Room> availableRooms, int roomsRequested) {
        return availableRooms.subList(0,roomsRequested);
    }
}