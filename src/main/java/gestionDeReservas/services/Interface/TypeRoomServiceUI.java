package gestionDeReservas.services.Interface;

import java.util.List;

import gestionDeReservas.Model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.EditRoomTypeDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.Model.entity.RoomType;

public interface TypeRoomServiceUI {
    
    RoomTypeGetDTO createTypeRoom(CreateTypeRoomDTO roomTOCreate);
    void deleteTypeRoom(Integer id);
    RoomTypeGetDTO editTypeRoom(EditRoomTypeDTO roomType);
    List<RoomTypeGetDTO> getAllTypesRooms();
    RoomType getTypeById(Integer id);
}
