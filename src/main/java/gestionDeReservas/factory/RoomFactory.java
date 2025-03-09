package gestionDeReservas.factory;

import gestionDeReservas.services.implementation.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gestionDeReservas.model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.RoomType;
import gestionDeReservas.services.implementation.RoomTypeService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RoomFactory {

    @Autowired
    RoomTypeService TypeRoomService;

    @Autowired
    ImageService imageService;

    public Room buildRoom(RoomCreateRequestDTO roomToCreate){

        Integer typeid = roomToCreate.typeRoomID();
        RoomType roomType = TypeRoomService.findRoomTypeById(typeid);

        return Room
                .builder()
                .name(roomToCreate.name())
                .roomNumber(roomToCreate.roomNumber())
                .description(roomToCreate.description())
                .capacity(roomType.getCapacity())
                .roomType(roomType)
                .build();
    }
}