package gestionDeReservas.services.Interface;

import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.dto.booking.BookingResponseDTO;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {
     void bookingRooms(UserEntity user, BookingRequestDTO bookingRequestDTO) ;
     List<RoomGetDTO> getAvailableRoomsDTO(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut);
     void delete(Integer bookingId);
     List<BookingResponseDTO> getAll(UserEntity user);
}