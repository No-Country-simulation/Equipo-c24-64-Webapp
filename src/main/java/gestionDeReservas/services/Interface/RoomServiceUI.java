package gestionDeReservas.services.Interface;

import java.time.LocalDate;
import java.util.List;

import gestionDeReservas.model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomEditRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.model.entity.Room;

public interface RoomServiceUI {
    List<RoomGetDTO> getAllRooms();
    RoomGetDTO getRoomById(int id) throws Exception;
    RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO) throws Exception;
    RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO) throws Exception;
    void deleteRoom(Integer id) throws Exception;
    void updateRoomsAvailable();
    List<RoomGetDTO> getAvailableRoomsDTO(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut);
    List<Room> getAvailableRooms(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut);
}
