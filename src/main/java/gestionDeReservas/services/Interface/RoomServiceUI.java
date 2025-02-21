package gestionDeReservas.services.Interface;

import java.util.List;

import gestionDeReservas.Model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomEditRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import org.springframework.web.multipart.MultipartFile;

public interface RoomServiceUI {
    List<RoomGetDTO> getAllRooms();
    RoomGetDTO getRoomById(int id);
    RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO, List<MultipartFile> files);
    RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO);
    RoomGetDTO uploadRoomImages(int id, List<MultipartFile> files);
    void deleteRoom(Integer id);

}
