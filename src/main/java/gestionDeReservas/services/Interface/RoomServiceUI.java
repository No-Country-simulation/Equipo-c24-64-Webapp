package gestionDeReservas.services.Interface;

import java.util.List;

import gestionDeReservas.Model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomEditRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;

public interface RoomServiceUI {
    
    List<RoomGetDTO> getAllRooms();
    RoomGetDTO getRoomById(int id);
    RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO);
    RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO);
    void deleteRoom(Integer id);

}
