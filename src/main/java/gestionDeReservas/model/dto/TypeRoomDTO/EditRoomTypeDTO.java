package gestionDeReservas.model.dto.TypeRoomDTO;

import lombok.Builder;

@Builder
public record EditRoomTypeDTO(
    Integer id,
    String name,   
    String description,
    Integer capacity,
    Double price
) {
    
}
