package gestionDeReservas.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestionDeReservas.Model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomEditRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.Model.entity.Room;
import gestionDeReservas.exception.NotRoomFoundException;
import gestionDeReservas.factory.RoomFactory;
import gestionDeReservas.mapper.RoomMapper;
import gestionDeReservas.repository.RoomRepository;
import gestionDeReservas.services.Interface.RoomServiceUI;
import jakarta.transaction.Transactional;

@Service
public class RoomService implements RoomServiceUI {
    
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomFactory roomFactory;

    @Override
    public List<RoomGetDTO> getAllRooms() {
        return roomRepository
        .findAll()
        .stream()
        .map((r) -> roomMapper.toGetDTO(r))
        .toList();
    }

    @Override
    public RoomGetDTO getRoomById(int id) {
        Room room = roomRepository
        .findById(id)
        .orElseThrow(() -> new NotRoomFoundException("room not found with id" + id)) ;

        return roomMapper.toGetDTO(room);
    }

    @Override
    public RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO) {
        Room room = roomFactory.buildRoom(roomCreateRequestDTO);
        return roomMapper.toGetDTO(roomRepository.save(room));
         
    }

    @Override
    public RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO) {
        Room room = roomRepository
        .findById(roomEditRequestDTO.id())
        .orElseThrow(() -> new NotRoomFoundException("room not found with id" + roomEditRequestDTO.id())) ;
        
        room.setName(roomEditRequestDTO.name());
        room.setDescription(roomEditRequestDTO.description());
        room.setCapacity(roomEditRequestDTO.capacity());
        return roomMapper.toGetDTO(roomRepository.save(room));
    }

    @Override
    @Transactional
    public void deleteRoom(Integer id) {
        roomRepository.delete(roomRepository
        .findById(id)
        .orElseThrow(() -> new NotRoomFoundException("room not found with id" + id)));
    }


}
