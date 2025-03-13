package gestionDeReservas.services.Interface;

import java.util.List;

import gestionDeReservas.model.entity.RoomType;
import org.springframework.web.multipart.MultipartFile;
import gestionDeReservas.model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.model.dto.TypeRoomDTO.EditRoomTypeDTO;
import gestionDeReservas.model.dto.TypeRoomDTO.RoomTypeGetDTO;

public interface RoomTypeServiceUI {
    RoomTypeGetDTO createTypeRoom(CreateTypeRoomDTO roomTOCreate,List<MultipartFile> files ) throws Exception;
    void deleteTypeRoom(Integer id) throws Exception;
    RoomTypeGetDTO editTypeRoom(EditRoomTypeDTO roomType) throws Exception;
    RoomTypeGetDTO uploadRoomTypeImages(int id, List<MultipartFile> files) throws Exception;
    void removeRoomImage(int roomId, int imageId) throws Exception;
    List<RoomTypeGetDTO> getAllTypesRooms();
    RoomType getRoomTypeById(Integer id);
}