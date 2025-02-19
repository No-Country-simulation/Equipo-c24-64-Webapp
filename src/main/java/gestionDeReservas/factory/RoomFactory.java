package gestionDeReservas.factory;

import org.springframework.stereotype.Component;

import gestionDeReservas.Model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.Model.entity.Room;

@Component
public class RoomFactory {
    
    public Room buildRoom(RoomCreateRequestDTO roomToCreate){
        return Room
        .builder()
        .name(roomToCreate.name())
        .description(roomToCreate.description())
        .capacity(roomToCreate.capacity())
        .build();
    }
}
