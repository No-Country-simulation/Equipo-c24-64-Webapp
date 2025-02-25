package gestionDeReservas.model.dto.TypeRoomDTO;

import java.util.List;

import gestionDeReservas.model.dto.ImageDTO.ImageGetDTO;
import lombok.Builder;

@Builder
public record RoomTypeGetDTO(
    String name,   
    String description,
    Integer capacity,
    Double price,
    List<ImageGetDTO> images
) {
    
}