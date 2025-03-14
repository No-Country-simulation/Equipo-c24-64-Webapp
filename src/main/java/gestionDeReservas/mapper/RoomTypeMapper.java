package gestionDeReservas.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import gestionDeReservas.model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.model.entity.RoomType;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoomTypeMapper {
    ImageMapper imageMapper;

    public RoomTypeGetDTO toGetDTO(RoomType type){
        return RoomTypeGetDTO.builder()
                .name(type.getName())
                .capacity(type.getCapacity())
                .description(type.getDescription())
                .price(type.getPrice())
                .images(type.getImages().stream().map(imageMapper::toImageGetDTO).toList())
                .build();
    }
}