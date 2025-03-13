package gestionDeReservas.services.implementation;

import java.time.LocalDate;
import java.util.List;

import gestionDeReservas.enums.RoomStatus;
import gestionDeReservas.exception.NotFoundException;
import gestionDeReservas.model.entity.RoomType;
import gestionDeReservas.services.Interface.RoomTypeServiceUI;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoomService implements RoomServiceUI {
    IRoomRepository roomRepository;
    RoomTypeServiceUI roomTypeService;
    RoomMapper roomMapper;
    RoomFactory roomFactory;

    @Override
    public List<RoomGetDTO> getAllRooms() {
        return roomRepository
        .findAll()
        .stream()
        .map(roomMapper::toGetDTO)
        .toList();
    }

    @Override
    public RoomGetDTO getRoomById(int id){
        Room room = getRoom(id);
        return roomMapper.toGetDTO(room);
    }

    @Override
    public RoomGetDTO  addRoom(RoomCreateRequestDTO roomCreateRequestDTO){
        Room room = roomFactory.buildRoom(roomCreateRequestDTO);
        return roomMapper.toGetDTO(roomRepository.save(room));
    }

    @Override
    public RoomGetDTO editRoom(RoomEditRequestDTO roomEditRequestDTO){
        Room room = getRoom(roomEditRequestDTO.id());
        
        room.setName(roomEditRequestDTO.name());
        room.setDescription(roomEditRequestDTO.description());
        room.setCapacity(roomEditRequestDTO.capacity());
        if (roomEditRequestDTO.typeRoomID() != null) {
            room.setRoomType(roomFactory
            .getTypeRoomService()
            .getRoomTypeById(roomEditRequestDTO.typeRoomID()));
        }
        return roomMapper.toGetDTO(roomRepository.save(room));
    }

    @Override
    @Transactional
    public void deleteRoom(Integer id){
        roomRepository.delete(roomRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("room not found with id" + id)));
    }

    @Override
    @Scheduled(cron = "0 0 8 * * *", zone = "America/Argentina/Buenos_Aires")
    public void updateRoomsAvailable() {
        List<Room> rooms = roomRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        rooms.forEach(room -> {
                    RoomStatus newStatus = roomNotIsAvailable(currentDate, room.getId())
                            ? RoomStatus.UNAVAILABLE
                            : RoomStatus.AVAILABLE;

                    if (room.getRoomStatus() != newStatus) {
                        room.setRoomStatus(newStatus);
                        roomRepository.save(room);
                    }
                });
    }

    @Override
    public List<RoomGetDTO> getAvailableRoomsDTO(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut) {
        return roomMapper.RoomGetAllDTO(getAvailableRooms(roomTypeId,checkIn,checkOut));
    }

    @Override
    public List<Room> getAvailableRooms(Integer roomTypeId, LocalDate checkIn, LocalDate checkOut) {
        RoomType roomType = roomTypeService.getRoomTypeById(roomTypeId);
        return roomType.getRooms().stream()
                .filter(room ->  !isRoomBooked(room.getId(), checkIn, checkOut))
                .toList();
    }

    private boolean isRoomBooked(Integer roomId, LocalDate checkIn, LocalDate checkOut) {
        return roomRepository.countOverlappingReservations(roomId, checkIn, checkOut);
    }

    private boolean roomNotIsAvailable(LocalDate currentDate, Integer roomId) {
        return roomRepository.notIsAvailable(currentDate,roomId);
    }

    private Room getRoom(Integer roomId){
        return  roomRepository
                .findById(roomId)
                .orElseThrow(() -> new NotFoundException("room not found with id" + roomId)) ;
    }
}