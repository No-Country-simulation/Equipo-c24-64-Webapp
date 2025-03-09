package gestionDeReservas.mapper;

import gestionDeReservas.model.dto.booking.BookingResponseDTO;
import gestionDeReservas.model.entity.Booking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingMapper {
    public BookingResponseDTO toGetDTO(Booking booking){
        return BookingResponseDTO.builder()
                .bookingDate(booking.getBookingDate())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .priceWithIva(booking.getTotalPriceWithIVA())
                .roomsNumber(booking.getRoomNumbers())
                .roomsQuantity(booking.getRoomNumbers().size())
                .build();
    }

        public List<BookingResponseDTO> BookingGetAllDTO(List<Booking> bookings){
        return bookings.stream().map(this::toGetDTO).toList();
    }
}