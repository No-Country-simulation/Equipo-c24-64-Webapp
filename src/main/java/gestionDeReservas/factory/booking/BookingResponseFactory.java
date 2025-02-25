package gestionDeReservas.factory.booking;

import gestionDeReservas.model.dto.booking.BookingResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookingResponseFactory {

    public BookingResponseDTO buildBookingResponse(Double bookingPrice,
                                                   LocalDate checkIn,
                                                   LocalDate checkOut
                                                   ){

        return BookingResponseDTO
                .builder()
                .BookingPrice(bookingPrice)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .build();
    }

}