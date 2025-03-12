package gestionDeReservas.hotel.services.implementation;

import gestionDeReservas.exception.*;
import gestionDeReservas.factory.booking.BookingFactory;
import gestionDeReservas.factory.booking.BookingMailFactory;
import gestionDeReservas.mapper.BookingMapper;
import gestionDeReservas.mapper.RoomMapper;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.*;
import gestionDeReservas.repository.*;
import gestionDeReservas.services.implementation.BookingImplService;
import gestionDeReservas.services.Interface.BookingMailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingImplServiceTest {

    @Mock private IBookingRepository bookingRepository;
    @Mock private IRoomTypeRepository roomTypeRepository;
    @Mock private IVisitorRepository visitorRepository;
    @Mock private IUserRepository userRepository;
    @Mock private BookingMailService bookingMailService;
    @Mock private BookingMailFactory bookingMailFactory;
    @Mock private BookingFactory bookingFactory;
    @Mock private BookingMapper bookingMapper;
    @Mock private RoomMapper roomMapper;

    @InjectMocks private BookingImplService bookingService;

    private BookingRequestDTO bookingRequestDTO;
    private RoomType roomType;

    @BeforeEach
    void setUp() {
        bookingRequestDTO = new BookingRequestDTO(
                "test@example.com",  // email
                1,  // idRoomType
                LocalDate.now().plusDays(2),  // checkIn
                LocalDate.now().plusDays(5),  // checkOut
                2,  // peopleQuantity (mínimo 1)
                1,  // roomsQuantity (mínimo 1)
                "Near the window"  // specialRequests (puede ser null o "")
        );

        roomType = new RoomType();
        roomType.setId(1);
        roomType.setName("Suite Deluxe");
    }

    @Test
    void shouldThrowNotFoundExceptionWhenGuestNotFound() {
        when(visitorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> bookingService.bookingRooms(bookingRequestDTO));
        assertEquals("guest not found", exception.getMessage());
    }
}
