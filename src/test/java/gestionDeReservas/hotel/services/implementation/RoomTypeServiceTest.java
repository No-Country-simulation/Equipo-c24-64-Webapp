package gestionDeReservas.hotel.services.implementation;

import gestionDeReservas.exception.NotFoundException;
import gestionDeReservas.factory.TypeRoomFactory;
import gestionDeReservas.mapper.RoomTypeMapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        roomType.setName("Suite Presidencial");

        roomTypeGetDTO = new RoomTypeGetDTO("Suite Presidencial", "Amplia y lujosa", 2, 500.0, new ArrayList<>());

    }

    @Test
    void testGetAllTypesRooms() {
        when(roomTypeRepository.findAll()).thenReturn(List.of(roomType));
        when(roomTypeMapper.toGetDTO(roomType)).thenReturn(roomTypeGetDTO);

        List<RoomTypeGetDTO> result = roomTypeService.getAllTypesRooms();

        assertEquals(1, result.size());
        assertEquals("Suite Presidencial", result.get(0).name());
        verify(roomTypeRepository, times(1)).findAll();
    }

    @Test
    void testGetTypeById() {
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));

        RoomType result = roomTypeService.getTypeById(1);

        assertNotNull(result);
        assertEquals("Suite Presidencial", result.getName());
        verify(roomTypeRepository, times(1)).findById(1);
    }

    @Test
    void testGetTypeById_NotFound() {
        when(roomTypeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roomTypeService.getTypeById(1));
    }

    @Test
    void testDeleteTypeRoom() throws Exception {
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));
        doNothing().when(roomTypeRepository).deleteById(1);

        roomTypeService.deleteTypeRoom(1);

        verify(roomTypeRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteTypeRoom_NotFound() {
        when(roomTypeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> roomTypeService.deleteTypeRoom(1));
    }
}
