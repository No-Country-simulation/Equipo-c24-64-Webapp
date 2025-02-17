package gestionDeReservas.services;

import java.util.List;

import gestionDeReservas.Model.dto.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomEditRequestDTO;
import gestionDeReservas.Model.dto.RoomGetDTO;

public interface RoomServiceUI {
    
    List<RoomGetDTO> getAllRooms();
    RoomGetDTO getRoomById(int id);
    RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO);
    RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO);
    void deleteRoom(int id);

}
