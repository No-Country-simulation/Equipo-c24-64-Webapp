package gestionDeReservas.Model.dto;

import jakarta.persistence.Column;
import lombok.Builder;

@Builder
public record RoomGetDTO(
    Integer id,
    String name,
    String description,
    Integer capacity
) {
    
}
