package gestionDeReservas.model.dto.booking;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
@Builder
public record BookingRequestDTO (
        @NotNull
        Integer idRoomType,
        @NotNull
        LocalDate checkIn,
        @NotNull
        LocalDate checkOut,
        @NotNull
        @Min(1)
        Integer peopleQuantity,
        @Min(1)
        Integer roomsQuantity
) {
}