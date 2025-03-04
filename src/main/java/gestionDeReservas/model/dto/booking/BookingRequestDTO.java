package gestionDeReservas.model.dto.booking;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingRequestDTO (
        @NotBlank
        String email,
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
        Integer roomsQuantity,

        String specialRequests
) {}