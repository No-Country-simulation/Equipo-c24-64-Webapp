package gestionDeReservas.hotel.services.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import gestionDeReservas.exception.NotFoundException;
import gestionDeReservas.exception.RoomTypeNotFoundException;
import gestionDeReservas.factory.TypeRoomFactory;
import gestionDeReservas.mapper.RoomTypeMapper;
import gestionDeReservas.model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.model.dto.TypeRoomDTO.EditRoomTypeDTO;
import gestionDeReservas.model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.model.entity.RoomType;
import gestionDeReservas.repository.IRoomTypeRepository;
import gestionDeReservas.services.implementation.RoomTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoomTypeServiceTest {

    @Mock
    private IRoomTypeRepository roomTypeRepository;

    @Mock
    private RoomTypeMapper roomTypeMapper;

    @Mock
    private TypeRoomFactory typeRoomFactory;

    @InjectMocks
    private RoomTypeService roomTypeService;

    private RoomType roomType;
    private RoomTypeGetDTO roomTypeGetDTO;

    @BeforeEach
    void setUp() {
        roomType = new RoomType();
        roomType.setId(1);
        roomType.setName("Deluxe");
        roomType.setDescription("Luxury room");
        roomType.setPrice(200.0);
        roomType.setCapacity(2);

        roomTypeGetDTO = new RoomTypeGetDTO("Deluxe", "Luxury room", 2, 200.0, List.of());
    }

    @Test
    void testGetAllTypesRooms() {
        when(roomTypeRepository.findAll()).thenReturn(Arrays.asList(roomType));
        when(roomTypeMapper.toGetDTO(roomType)).thenReturn(roomTypeGetDTO);

        List<RoomTypeGetDTO> result = roomTypeService.getAllTypesRooms();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Deluxe", result.get(0).name());
        verify(roomTypeRepository, times(1)).findAll();
    }

    @Test
    void testGetTypeById_Success() throws Exception {
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));

        RoomType result = roomTypeService.getTypeById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(roomTypeRepository, times(1)).findById(1);
    }

    @Test
    void testGetTypeById_NotFound() {
        when(roomTypeRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RoomTypeNotFoundException.class, () -> roomTypeService.getTypeById(99));
        verify(roomTypeRepository, times(1)).findById(99);
    }

    @Test
    void testDeleteTypeRoom_Success() throws Exception {
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));
        doNothing().when(roomTypeRepository).deleteById(1);

        assertDoesNotThrow(() -> roomTypeService.deleteTypeRoom(1));

        verify(roomTypeRepository, times(1)).findById(1);
        verify(roomTypeRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteTypeRoom_NotFound() {
        when(roomTypeRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RoomTypeNotFoundException.class, () -> roomTypeService.deleteTypeRoom(99));
        verify(roomTypeRepository, times(1)).findById(99);
        verify(roomTypeRepository, never()).deleteById(anyInt());
    }
}
