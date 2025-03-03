package gestionDeReservas.services.Interface;

import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
     void bookingRooms(UserEntity user, BookingRequestDTO bookingRequestDTO) ;
     List<RoomGetDTO> getAvailableRoomsDTO(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut);
}