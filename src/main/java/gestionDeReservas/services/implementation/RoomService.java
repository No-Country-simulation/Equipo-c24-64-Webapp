package gestionDeReservas.services.implementation;

import java.util.List;

import gestionDeReservas.Model.entity.Image;
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
import org.springframework.web.multipart.MultipartFile;

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
    public RoomGetDTO getRoomById(int id) throws Exception{
        Room room = findRoomById(id);
        return roomMapper.toGetDTO(room);
    }

    @Override
    public RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO) throws Exception {
        Room room = roomFactory.buildRoom(roomCreateRequestDTO);
        return roomMapper.toGetDTO(roomRepository.save(room));
    }

    @Override
    public RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO) throws Exception {
        Room room = findRoomById(roomEditRequestDTO.id());
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
    @Transactional
    public void deleteRoom(Integer id) throws Exception {
        roomRepository.delete(findRoomById(id));
    }

    private Room findRoomById(int id) {
        return roomRepository
                .findById(id)
                .orElseThrow(() -> new NotRoomFoundException("room not found with id" + id));
    }
}