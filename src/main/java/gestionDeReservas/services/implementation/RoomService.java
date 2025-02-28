package gestionDeReservas.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestionDeReservas.Model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomEditRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.Model.entity.Room;
import gestionDeReservas.exception.NotRoomFoundException;
import gestionDeReservas.exception.RoomTypeNotFoundException;
import gestionDeReservas.factory.RoomFactory;
import gestionDeReservas.mapper.RoomMapper;
import gestionDeReservas.repository.RoomRepository;
import gestionDeReservas.services.Interface.RoomServiceUI;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomService implements RoomServiceUI {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final RoomFactory roomFactory;


    @Override
    public List<RoomGetDTO> getAllRooms() {
        return roomRepository
        .findAll()
        .stream()
        .map((r) -> roomMapper.toGetDTO(r))
        .toList();
    }

    @Override
    public RoomGetDTO getRoomById(int id) throws Exception{
        log.info("getting room");
        
        Room room = roomRepository
        .findById(id)
        .orElseThrow(() -> new NotRoomFoundException("room not found with id" + id)) ;
       
        return roomMapper.toGetDTO(room);
    }

    @Override
    public RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO) throws Exception {
        Room room = roomFactory.buildRoom(roomCreateRequestDTO);
        return roomMapper.toGetDTO(roomRepository.save(room));
         
    }

    @Override
    public RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO) throws Exception {
        Room room = roomRepository
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
        return roomMapper.toGetDTO(roomRepository.save(room));
    }

    @Override
    public void deleteRoom(Integer id) throws Exception {
        roomRepository.delete(roomRepository
        .findById(id)
        .orElseThrow(() -> new NotRoomFoundException("room not found with id" + id)));
    }

}