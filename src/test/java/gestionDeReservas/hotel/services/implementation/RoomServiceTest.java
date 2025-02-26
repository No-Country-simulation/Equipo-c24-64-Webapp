package gestionDeReservas.hotel.services.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import gestionDeReservas.exception.NotRoomFoundException;
import gestionDeReservas.mapper.RoomMapper;
import gestionDeReservas.model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.model.dto.TypeRoomDTO.RoomTypeGetDTO;
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
    private RoomTypeGetDTO roomTypeGetDTO;

    @BeforeEach
    void setUp() {
        roomTypeGetDTO = new RoomTypeGetDTO("Suite", "Habitación de lujo", 2, 150.00, new ArrayList<>()); // Se ajustan los parámetros

        room = new Room();
        room.setId(1);
        room.setName("Suite");
        room.setDescription("Luxury suite");
        room.setCapacity(2);

        roomGetDTO = new RoomGetDTO(1, "Suite", "Luxury suite", 2, roomTypeGetDTO); // Ahora coincide con el constructor
    }

    @Test
    void testGetAllRooms() {
        when(roomRepository.findAll()).thenReturn(Arrays.asList(room));
        when(roomMapper.toGetDTO(room)).thenReturn(roomGetDTO);

        List<RoomGetDTO> rooms = roomService.getAllRooms();

        assertNotNull(rooms);
        assertEquals(1, rooms.size());
        assertEquals("Suite", rooms.get(0).name());
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void testGetRoomById_Success() throws Exception {
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        when(roomMapper.toGetDTO(room)).thenReturn(roomGetDTO);

        RoomGetDTO result = roomService.getRoomById(1);

        assertNotNull(result);
        assertEquals(1, result.id());
        verify(roomRepository, times(1)).findById(1);
    }

    @Test
    void testGetRoomById_NotFound() {
        when(roomRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(NotRoomFoundException.class, () -> roomService.getRoomById(99));
        verify(roomRepository, times(1)).findById(99);
    }

    @Test
    void testAddRoom() throws Exception {
        RoomCreateRequestDTO requestDTO = new RoomCreateRequestDTO("Suite", "Luxury suite", 2); // Se eliminó typeRoomID si no es necesario
        when(roomFactory.buildRoom(requestDTO)).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(room);
        when(roomMapper.toGetDTO(room)).thenReturn(roomGetDTO);

        RoomGetDTO result = roomService.addRoom(requestDTO);

        assertNotNull(result);
        assertEquals("Suite", result.name());
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testDeleteRoom_Success() throws Exception {
        when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        doNothing().when(roomRepository).delete(room);

        assertDoesNotThrow(() -> roomService.deleteRoom(1));

        verify(roomRepository, times(1)).findById(1);
        verify(roomRepository, times(1)).delete(room);
    }

    @Test
    void testDeleteRoom_NotFound() {
        when(roomRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(NotRoomFoundException.class, () -> roomService.deleteRoom(99));

        verify(roomRepository, times(1)).findById(99);
        verify(roomRepository, never()).delete(any(Room.class));
    }
}
