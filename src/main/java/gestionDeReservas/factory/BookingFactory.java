package gestionDeReservas.factory;

import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.Booking;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingFactory {
    public Booking buildBooking(BookingRequestDTO bookingRequestDTO, UserEntity user,
                                Room room, Double BookingPrice){
        return Booking.builder()
                .checkIn(bookingRequestDTO.checkIn())
                .checkOut(bookingRequestDTO.checkOut())
                .totalPrice(BookingPrice)
                .peopleQuantity(bookingRequestDTO.peopleQuantity())
                .room(room)
                .userEntity(user)
                .build();
    }
}
