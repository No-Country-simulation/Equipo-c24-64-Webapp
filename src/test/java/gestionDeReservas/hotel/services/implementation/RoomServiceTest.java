package gestionDeReservas.hotel.services.implementation;

import gestionDeReservas.exception.NotFoundException;
import gestionDeReservas.mapper.RoomMapper;
import gestionDeReservas.model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomEditRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.repository.IRoomRepository;
import gestionDeReservas.factory.RoomFactory;
import gestionDeReservas.services.implementation.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private IRoomRepository roomRepository;

    @Mock
    private RoomMapper roomMapper;

    @Mock
    private RoomFactory roomFactory;

    @InjectMocks
    private RoomService roomService;

    private Room room;
    private RoomGetDTO roomGetDTO;

    @BeforeEach
    void setUp() {
        room = new Room();
        room.setId(1);
        room.setName("Habitación Deluxe");

        roomGetDTO = new RoomGetDTO(1, 101, "Habitación Deluxe", "Cómoda y espaciosa", 2, null);
    }

    @Test
    void testGetAllRooms() {
        when(roomRepository.findAll()).thenReturn(List.of(room));
        when(roomMapper.toGetDTO(room)).thenReturn(roomGetDTO);

        List<RoomGetDTO> result = roomService.getAllRooms();

        assertEquals(1, result.size());
        assertEquals("Habitación Deluxe", result.get(0).name());
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void testGetRoomById() throws Exception {
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        when(roomMapper.toGetDTO(room)).thenReturn(roomGetDTO);

        RoomGetDTO result = roomService.getRoomById(1);

        assertNotNull(result);
        assertEquals("Habitación Deluxe", result.name());
        verify(roomRepository, times(1)).findById(1);
    }

    @Test
    void testGetRoomById_NotFound() {
        when(roomRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roomService.getRoomById(1));
    }

    @Test
    void testAddRoom() throws Exception {
        RoomCreateRequestDTO requestDTO = new RoomCreateRequestDTO("Habitación Deluxe", "Cómoda y espaciosa", 2, null);

        when(roomFactory.buildRoom(requestDTO)).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(room);
        when(roomMapper.toGetDTO(room)).thenReturn(roomGetDTO);

        RoomGetDTO result = roomService.addRoom(requestDTO);

        assertNotNull(result);
        assertEquals("Habitación Deluxe", result.name());
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testEditRoom() throws Exception {
        RoomEditRequestDTO requestDTO = new RoomEditRequestDTO(1, "Habitación VIP", "Muy lujosa", 4, null);

        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        when(roomRepository.save(any(Room.class))).thenReturn(room);
        when(roomMapper.toGetDTO(any(Room.class))).thenReturn(new RoomGetDTO(1, 202, "Habitación VIP", "Muy lujosa", 4, null));

        RoomGetDTO result = roomService.editRoom(requestDTO);

        assertNotNull(result);
        assertEquals("Habitación VIP", result.name());
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    void testDeleteRoom() throws Exception {
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        doNothing().when(roomRepository).delete(room);

        roomService.deleteRoom(1);

        verify(roomRepository, times(1)).delete(room);
    }

    @Test
    void testDeleteRoom_NotFound() {
        when(roomRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roomService.deleteRoom(1));
    }
}
