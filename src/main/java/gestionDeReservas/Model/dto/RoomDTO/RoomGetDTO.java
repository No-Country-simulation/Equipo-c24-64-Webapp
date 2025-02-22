package gestionDeReservas.Model.dto.RoomDTO;

import gestionDeReservas.Model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.Model.entity.RoomType;
import lombok.Builder;

@Builder
public record RoomGetDTO(
    Integer id,
    String name,
    String description,
    Integer capacity,
    RoomTypeGetDTO typeRoom
) {
    
}
