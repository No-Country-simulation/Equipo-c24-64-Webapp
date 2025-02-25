package gestionDeReservas.Model.dto.TypeRoomDTO;

import lombok.Builder;

@Builder
public record CreateTypeRoomDTO(
    String name,   
    String description,
    Integer capacity,
    Double price
) {
    
}
