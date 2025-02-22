package gestionDeReservas.Model.dto.TypeRoomDTO;

import gestionDeReservas.Model.dto.ImageDTO.ImageGetDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.Builder;

import java.util.List;

@Builder
public record RoomTypeGetDTO(
    String name,   
    String description,
    Integer capacity,
    Double price,
    List<ImageGetDTO> images
) {
    
}
