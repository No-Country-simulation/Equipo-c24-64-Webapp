package gestionDeReservas.Model.dto.RoomDTO;

import gestionDeReservas.Model.dto.ImageDTO.ImageGetDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.Model.entity.RoomType;
import lombok.Builder;

import java.util.List;

@Builder
public record RoomGetDTO(
    Integer id,
    String name,
    String description,
    Integer capacity,
    RoomTypeGetDTO typeRoom,
    List<ImageGetDTO> images
) {
    
}
