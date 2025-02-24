package gestionDeReservas.services.Interface;

import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.dto.booking.BookingResponseDTO;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
     BookingResponseDTO saveBooking(UserEntity user, BookingRequestDTO bookingRequestDTO) ;
     List<Room> getEnableRooms(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut);
}