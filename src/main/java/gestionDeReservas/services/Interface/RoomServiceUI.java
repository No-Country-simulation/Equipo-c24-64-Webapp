package gestionDeReservas.services.Interface;

import java.util.List;

import gestionDeReservas.Model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomEditRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import org.springframework.web.multipart.MultipartFile;

public interface RoomServiceUI {
    List<RoomGetDTO> getAllRooms();
    RoomGetDTO getRoomById(int id) throws Exception;
    RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO, List<MultipartFile> imagesRequest) throws Exception;
    RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO) throws Exception;
    RoomGetDTO uploadRoomImages(int id, List<MultipartFile> files) throws Exception;
    void removeRoomImage(int roomId, int imageId) throws Exception;
    void deleteRoom(Integer id) throws Exception;
}
