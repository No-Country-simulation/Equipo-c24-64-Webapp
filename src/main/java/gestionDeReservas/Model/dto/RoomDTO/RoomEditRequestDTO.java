package gestionDeReservas.Model.dto.RoomDTO;

import lombok.Builder;

@Builder
public record RoomEditRequestDTO(
    Integer id,
    String name,
    String description,
    Integer capacity
) {
    
}
