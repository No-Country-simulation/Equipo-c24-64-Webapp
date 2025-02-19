package gestionDeReservas.Model.dto.RoomDTO;

import lombok.Builder;

@Builder
public record RoomGetDTO(
    Integer id,
    String name,
    String description,
    Integer capacity
) {
    
}
