package gestionDeReservas.factory;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import gestionDeReservas.model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.RoomType;

@Component
public class TypeRoomFactory {
    
    public RoomType buildRoomType(CreateTypeRoomDTO type){
        return RoomType
        .builder()
        .name(type.name())
        .capacity(type.capacity())
        .description(type.description())
        .price(type.price())
        .rooms(new ArrayList<Room>())
        .build();
    }
}
