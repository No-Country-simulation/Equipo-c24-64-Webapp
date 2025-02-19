package gestionDeReservas.mapper;

import org.springframework.stereotype.Component;

import gestionDeReservas.Model.dto.RoomGetDTO;
import gestionDeReservas.Model.entity.Room;

@Component
public class RoomMapper {
    
    public RoomGetDTO toGetDTO(Room room){
        return RoomGetDTO
        .builder()
        .id(room.getId())
        .name(room.getName())
        .description(room.getDescription())
        .capacity(room.getCapacity())
        .build();
    }

}
