package gestionDeReservas.mapper;

import org.springframework.stereotype.Component;

import gestionDeReservas.model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.model.entity.RoomType;

@Component
public class RoomTypeMapper {
    
    public RoomTypeGetDTO toGetDTO(RoomType type){
        return RoomTypeGetDTO.builder()
        .name(type.getName())
        .capacity(type.getCapacity())
        .description(type.getDescription())
        .price(type.getPrice())
        .build();
        
    }
}
