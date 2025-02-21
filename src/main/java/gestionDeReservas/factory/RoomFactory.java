package gestionDeReservas.factory;

import gestionDeReservas.Model.entity.RoomType;
import gestionDeReservas.services.implementation.RoomTypeService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gestionDeReservas.Model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.Model.entity.Room;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RoomFactory {

    @Autowired
    RoomTypeService TypeRoomService;
    
    public Room buildRoom(RoomCreateRequestDTO roomToCreate){
        Integer typeid = roomToCreate.typeRoomID();
        RoomType roomType = TypeRoomService.findById(typeid);

        return Room
                .builder()
                .name(roomToCreate.name())
                .description(roomToCreate.description())
                .capacity(roomType.getCapacity())
                .roomType(roomType)
                .build();
    }
}
