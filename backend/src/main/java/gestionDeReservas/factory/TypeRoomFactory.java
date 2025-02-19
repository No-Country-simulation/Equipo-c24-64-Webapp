package gestionDeReservas.factory;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import gestionDeReservas.Model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.Model.entity.Room;
import gestionDeReservas.Model.entity.RoomType;

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
