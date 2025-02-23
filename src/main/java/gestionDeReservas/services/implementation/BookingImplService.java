package gestionDeReservas.services.implementation;

import gestionDeReservas.exception.BadRequestException;
import gestionDeReservas.exception.NotRoomFoundException;
import gestionDeReservas.factory.BookingFactory;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.Booking;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.RoomType;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.repository.IBookingRepository;
import gestionDeReservas.repository.IRoomRepository;
import gestionDeReservas.services.Interface.IBookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BookingImplService implements IBookingService {
    IBookingRepository bookingRepository;
    IRoomRepository roomRepository;
    BookingFactory bookingFactory;

    @Override
    public void saveBooking(UserEntity user, BookingRequestDTO bookingRequestDTO) {
        Room room = findRoom(bookingRequestDTO.idRoom());
        validateBooking(bookingRequestDTO);

        RoomType roomType = room.getRoomType();
        Long stayDuration = calculateHotelStayDuration(bookingRequestDTO.checkIn(),bookingRequestDTO.checkOut());
        Double bookingPrice = roomType.getPrice() * stayDuration;

        Booking booking = bookingFactory.buildBooking(bookingRequestDTO,user,room,bookingPrice);
        bookingRepository.save(booking);
    }

    private Room findRoom(Integer idRoom) {
        return roomRepository.findById(idRoom)
                .orElseThrow(() -> new NotRoomFoundException("not found room"));
    }

    private void validateBooking(BookingRequestDTO bookingRequestDTO) {
        if(isRoomBooked(bookingRequestDTO.idRoom(),bookingRequestDTO.checkIn()
        ,bookingRequestDTO.checkOut()) > 0){
            throw new BadRequestException("la habitacion con id: "+bookingRequestDTO.idRoom()
            +"already exists");
        }
    }

    private Long isRoomBooked(Integer idRoom, LocalDate checkIn, LocalDate checkOut) {
        return bookingRepository.countOverlappingReservations(idRoom,checkIn,checkOut);
    }

    private Long calculateHotelStayDuration(LocalDate checkInDate, LocalDate checkOutDate) {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }
}