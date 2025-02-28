package gestionDeReservas.Model.dto.RoomDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record  RoomCreateRequestDTO(
    @Min(2)
    @Max(100)
    String name,
    @Min(2)
    @Max(100)
    String description,
    @Min(0)
    Integer typeRoomID
) {
    
}
