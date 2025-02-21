package gestionDeReservas.Model.dto.RoomDTO;

import gestionDeReservas.Model.dto.ImageDTO.ImageGetDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record RoomGetDTO(
    Integer id,
    String name,
    String description,
    Integer capacity,
    List<ImageGetDTO> images
) {
    
}
