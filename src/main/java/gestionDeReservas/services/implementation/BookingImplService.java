package gestionDeReservas.services.implementation;

import gestionDeReservas.exception.*;
import gestionDeReservas.factory.booking.BookingFactory;
import gestionDeReservas.factory.booking.BookingMailFactory;
import gestionDeReservas.mapper.BookingMapper;
import gestionDeReservas.mapper.RoomMapper;
import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.model.dto.booking.BookingMailDTO;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.dto.booking.BookingResponseDTO;
import gestionDeReservas.model.entity.*;
import gestionDeReservas.repository.IBookingRepository;
import gestionDeReservas.repository.IRoomTypeRepository;
import gestionDeReservas.repository.IUserRepository;
import gestionDeReservas.repository.IVisitorRepository;
import gestionDeReservas.services.Interface.BookingMailService;
import gestionDeReservas.services.Interface.BookingService;
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
    IBookingRepository bookingRepository;
    IRoomTypeRepository roomTypeRepository;
    IVisitorRepository visitorRepository;
    IUserRepository userRepository;
    BookingMailService bookingMailService;
    BookingMailFactory bookingMailFactory;
    BookingFactory bookingFactory;
    BookingMapper bookingMapper;
    RoomMapper roomMapper;

    @Override
    public void bookingRooms(BookingRequestDTO bookingRequestDTO) {
        String email = bookingRequestDTO.email();
        Visitor visitor = visitorRepository.findByEmail(email).orElse(null);
        User user = userRepository.findByEmail(email).orElse(null);

        validateGuest(visitor,user);

        RoomType roomType = findRoomType(bookingRequestDTO.idRoomType());
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

    private void CreateBookingEmail(Booking booking) {
        BookingMailDTO bookingMail = bookingMailFactory.buildBookingMail(booking);
        bookingMailService.sendBookingMail(bookingMail);
    }

    @Override
    public List<RoomGetDTO> getAvailableRoomsDTO(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut) {
        return roomMapper.RoomGetAllDTO(getAvailableRooms(roomTypeId,checkIn,checkOut));
    }

    @Override
    public List<BookingResponseDTO> getBookingsFromuser(String email) {
        return  bookingMapper.BookingGetAllDTO(bookingRepository.findBookingsFromUser(email));
    }

    private Booking getBooking(BookingRequestDTO bookingRequestDTO, User user, Visitor visitor, List<Room> bookingRooms) {
        if(user == null)
            return bookingFactory.buildVisitorBooking(bookingRequestDTO,visitor,bookingRooms);

        return bookingFactory.buildBooking(bookingRequestDTO, user, bookingRooms);
    }

    private void validateGuest(Visitor visitor, User user) {
        if(visitor == null && user == null)
            throw new NotFoundException("guest not found");
    }

    private List<Room> getAvailableRooms(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut) {
        RoomType roomType = findRoomType(roomTypeId);
        return roomType.getRooms().stream()
                .filter(room ->  !isRoomBooked(room.getId(), checkIn, checkOut))
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
                .orElseThrow(() -> new NotFoundException("Room type not found"));
    }

    private void validateDates(LocalDate checkIn, LocalDate checkOut) {
        LocalDate currentDate = LocalDate.now();
        LocalDate validDate = checkIn.plusDays(1);
        if (checkOut.isBefore(validDate) || checkIn.isBefore(currentDate))
            throw new DateRangeException("The date range is invalid");
    }
}