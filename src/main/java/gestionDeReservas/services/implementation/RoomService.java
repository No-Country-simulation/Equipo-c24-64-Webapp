package gestionDeReservas.services.implementation;

import java.time.LocalDate;
import java.util.List;

import gestionDeReservas.enums.RoomStatus;
import gestionDeReservas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import gestionDeReservas.model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomEditRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.model.entity.Room;
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
        Room room = getRoom(id);
        return roomMapper.toGetDTO(room);
    }

    @Override
    public RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO) throws Exception {
        Room room = roomFactory.buildRoom(roomCreateRequestDTO);
        return roomMapper.toGetDTO(IRoomRepository.save(room));
    }

    @Override
    public RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO) throws Exception {
        Room room = getRoom(roomEditRequestDTO.id());
        
        room.setName(roomEditRequestDTO.name());
        room.setDescription(roomEditRequestDTO.description());
        room.setCapacity(roomEditRequestDTO.capacity());
        if (roomEditRequestDTO.typeRoomID() != null) {
            room.setRoomType(roomFactory
            .getTypeRoomService()
            .findRoomTypeById(roomEditRequestDTO.typeRoomID()));
        }
        return roomMapper.toGetDTO(IRoomRepository.save(room));
    }

    @Override
    @Transactional
    public void deleteRoom(Integer id) throws Exception {
        IRoomRepository.delete(IRoomRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("room not found with id" + id)));
    }

    @Override
    @Scheduled(cron = "0 0 8 * * *", zone = "America/Argentina/Buenos_Aires")
    public void checkStatusRooms() {
        List<Room> rooms = IRoomRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        rooms.forEach(room -> {
                    RoomStatus newStatus = roomNotIsAvailable(currentDate, room.getId())
                            ? RoomStatus.UNAVAILABLE
                            : RoomStatus.AVAILABLE;

                    if (room.getRoomStatus() != newStatus) {
                        room.setRoomStatus(newStatus);
                        IRoomRepository.save(room);
                    }
                });
    }

    private boolean roomNotIsAvailable(LocalDate currentDate, Integer roomId) {
        return IRoomRepository.notIsAvailable(currentDate,roomId);
    }

    private Room getRoom(Integer roomId){
        return  IRoomRepository
                .findById(roomId)
                .orElseThrow(() -> new NotFoundException("room not found with id" + roomId)) ;
    }
}