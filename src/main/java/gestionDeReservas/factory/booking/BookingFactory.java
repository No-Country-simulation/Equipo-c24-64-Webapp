package gestionDeReservas.factory.booking;

import gestionDeReservas.exception.NotRoomFoundException;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.Booking;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.RoomType;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.repository.IRoomTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class BookingFactory {
    IRoomTypeRepository roomTypeRepository;

    public Booking buildBooking(BookingRequestDTO bookingRequestDTO, UserEntity user,
                                Room room){

        RoomType roomType = roomTypeRepository.findById(bookingRequestDTO.idRoomType())
                .orElseThrow(() -> new NotRoomFoundException("no se encontro tipo de habitacion"));

        Long stayDuration =  calculateHotelStayDuration(bookingRequestDTO.checkIn(),bookingRequestDTO.checkOut());
        Double bookingPrice = roomType.getPrice() * stayDuration;

        return Booking.builder()
                .checkIn(bookingRequestDTO.checkIn())
                .checkOut(bookingRequestDTO.checkOut())
                .totalPrice(bookingPrice)
                .peopleQuantity(bookingRequestDTO.peopleQuantity())
                .room(room)
                .userEntity(user)
                .build();
    }

    private Long calculateHotelStayDuration(LocalDate checkInDate, LocalDate checkOutDate) {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate) + 1;

    }
}
