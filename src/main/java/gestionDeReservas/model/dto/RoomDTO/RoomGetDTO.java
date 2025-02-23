package gestionDeReservas.model.dto.RoomDTO;

import gestionDeReservas.model.dto.TypeRoomDTO.RoomTypeGetDTO;
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
