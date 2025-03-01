package gestionDeReservas.model.dto.booking;

import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public record EditBookingRequestDTO(
        Integer bookingId,
        Integer idRoomType,
        LocalDate checkIn,
        LocalDate checkOut,
        @Min(1)
        Integer peopleQuantity,
        @Min(1)
        Integer roomsQuantity
) {
}
