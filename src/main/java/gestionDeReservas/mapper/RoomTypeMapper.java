package gestionDeReservas.mapper;

import org.springframework.stereotype.Component;

import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.Model.entity.RoomType;

@Component
public class RoomTypeMapper {
    
    public RoomTypeGetDTO toGetDTO(RoomType type){
        return RoomTypeGetDTO.builder()
        .name(type.getName())
        .capacity(type.getCapacity())
        .description(type.getDescription())
        .build();
        
    }
}
