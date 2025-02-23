package gestionDeReservas.model.dto.booking;
import java.time.LocalDate;

public record BookingRequestDTO (
        Integer idRoomType,
        LocalDate checkIn,
        LocalDate checkOut,
        Integer peopleQuantity
) {
}