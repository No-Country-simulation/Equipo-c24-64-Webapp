package gestionDeReservas.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestionDeReservas.model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomEditRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.exception.NotRoomFoundException;
import gestionDeReservas.factory.RoomFactory;
import gestionDeReservas.mapper.RoomMapper;
import gestionDeReservas.repository.IRoomRepository;
import gestionDeReservas.services.Interface.RoomServiceUI;
import jakarta.transaction.Transactional;

@Service
public class RoomService implements RoomServiceUI {
    
    @Autowired
    private IRoomRepository IRoomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomFactory roomFactory;

    @Override
    public List<RoomGetDTO> getAllRooms() {
        return IRoomRepository
        .findAll()
        .stream()
        .map((r) -> roomMapper.toGetDTO(r))
        .toList();
    }

    @Override
    public RoomGetDTO getRoomById(int id) throws Exception{
        Room room = IRoomRepository
        .findById(id)
        .orElseThrow(() -> new NotRoomFoundException("room not found with id" + id)) ;

        return roomMapper.toGetDTO(room);
    }

    @Override
    public RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO) throws Exception {
        Room room = roomFactory.buildRoom(roomCreateRequestDTO);
        return roomMapper.toGetDTO(IRoomRepository.save(room));
         
    }

    @Override
    public RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO) throws Exception {
        Room room = IRoomRepository
        .findById(roomEditRequestDTO.id())
        .orElseThrow(() -> new NotRoomFoundException("room not found with id" + roomEditRequestDTO.id())) ;
        
        room.setName(roomEditRequestDTO.name());
        room.setDescription(roomEditRequestDTO.description());
        room.setCapacity(roomEditRequestDTO.capacity());
        if (roomEditRequestDTO.typeRoomID() != null) {
            room.setRoomType(roomFactory
            .getTypeRoomService()
            .findById(roomEditRequestDTO.typeRoomID()));
        }
        return roomMapper.toGetDTO(IRoomRepository.save(room));
    }

    @Override
    @Transactional
    public void deleteRoom(Integer id) throws Exception {
        IRoomRepository.delete(IRoomRepository
        .findById(id)
        .orElseThrow(() -> new NotRoomFoundException("room not found with id" + id)));
    }

}