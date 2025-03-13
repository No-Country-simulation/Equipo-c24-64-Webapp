package gestionDeReservas.services.Interface;

import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.dto.booking.BookingResponseDTO;

import java.util.List;

public interface BookingService {
     void bookingRooms(BookingRequestDTO bookingRequestDTO) ;
     List<BookingResponseDTO> getBookingsFromuser(String email);
}