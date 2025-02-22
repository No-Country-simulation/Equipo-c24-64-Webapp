package gestionDeReservas.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.Model.entity.Room;
import gestionDeReservas.Model.entity.RoomType;
import gestionDeReservas.repository.TypeRoomRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomMapper {


    @Autowired
    RoomTypeMapper roomTypeMapper;



    public RoomGetDTO toGetDTO(Room room){
        return RoomGetDTO
                .builder()
                .id(room.getId())
                .name(room.getName())
                .description(room.getDescription())
                .capacity(room.getCapacity())
                .typeRoom(roomTypeMapper.toGetDTO(room.getRoomType()))
                .build();
    }

}
