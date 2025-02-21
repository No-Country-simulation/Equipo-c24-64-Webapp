package gestionDeReservas.mapper;

import gestionDeReservas.Model.dto.ImageDTO.ImageGetDTO;
import org.springframework.stereotype.Component;

import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.Model.entity.Room;

@Component
public class RoomMapper {
    
    public RoomGetDTO toGetDTO(Room room){
        ImageMapper imageMapper = new ImageMapper();

        return RoomGetDTO
                .builder()
                .id(room.getId())
                .name(room.getName())
                .description(room.getDescription())
                .capacity(room.getCapacity())
                .images(room.getImages()
                        .stream()
                        .map(imageMapper::toImageGetDTO).toList())
                .build();
    }

}
