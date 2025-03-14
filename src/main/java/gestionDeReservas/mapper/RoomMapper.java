package gestionDeReservas.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.model.entity.Room;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class RoomMapper {
    RoomTypeMapper roomTypeMapper;

    public RoomGetDTO toGetDTO(Room room){
        return RoomGetDTO
                .builder()
                .roomStatus(room.getRoomStatus())
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .name(room.getName())
                .description(room.getDescription())
                .capacity(room.getCapacity())
                .typeRoom(roomTypeMapper.toGetDTO(room.getRoomType()))
                .build();
    }

    public List<RoomGetDTO>RoomGetAllDTO(List<Room> rooms){
        return rooms.stream().map(this::toGetDTO).toList();
    }
}