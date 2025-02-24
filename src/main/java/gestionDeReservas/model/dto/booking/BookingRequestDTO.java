package gestionDeReservas.model.dto.booking;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingRequestDTO (
        @NotNull
        Integer idRoomType,
        @NotNull
        LocalDate checkIn,
        @NotNull
        LocalDate checkOut,
        @NotNull
        @Min(value = 1)
        Integer peopleQuantity
) {
}