package gestionDeReservas.services.Interface;

import java.util.List;

import gestionDeReservas.model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomEditRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;

public interface RoomServiceUI {
    List<RoomGetDTO> getAllRooms();
    RoomGetDTO getRoomById(int id) throws Exception;
    RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO) throws Exception;
    RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO) throws Exception;
    void deleteRoom(Integer id) throws Exception;
}
