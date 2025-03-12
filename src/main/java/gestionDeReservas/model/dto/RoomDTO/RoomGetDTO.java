package gestionDeReservas.model.dto.RoomDTO;

import gestionDeReservas.enums.RoomStatus;
import gestionDeReservas.model.dto.TypeRoomDTO.RoomTypeGetDTO;
import lombok.Builder;

@Builder
public record RoomGetDTO(
    Integer id,
    RoomStatus roomStatus,
    Integer roomNumber,
    String name,
    String description,
    Integer capacity,
    RoomTypeGetDTO typeRoom
) {
    
}