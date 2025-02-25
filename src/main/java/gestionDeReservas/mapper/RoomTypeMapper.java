package gestionDeReservas.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gestionDeReservas.model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.model.entity.RoomType;

@Component
public class RoomTypeMapper {

    @Autowired
    ImageMapper imageMapper;

    public RoomTypeGetDTO toGetDTO(RoomType type){
        return RoomTypeGetDTO.builder()
                .name(type.getName())
                .capacity(type.getCapacity())
                .description(type.getDescription())
                .price(type.getPrice())
                .images(type.getImages().stream().map(image-> imageMapper.toImageGetDTO(image)).toList())
                .build();
    }
}
