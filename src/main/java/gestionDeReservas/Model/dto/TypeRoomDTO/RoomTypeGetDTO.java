package gestionDeReservas.Model.dto.TypeRoomDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.Builder;

@Builder
public record RoomTypeGetDTO(
    String name,   
    String description,
    Integer capacity,
    Double price
) {
    
}
