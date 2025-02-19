package gestionDeReservas.Model.dto;

import lombok.Builder;

@Builder
public record RoomGetDTO(
    Integer id,
    String name,
    String description,
    Integer capacity
) {
    
}
