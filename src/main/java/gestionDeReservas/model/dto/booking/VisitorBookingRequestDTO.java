package gestionDeReservas.model.dto.booking;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record VisitorBookingRequestDTO(
        @NotNull
        Integer idRoomType,
        @Email(message = "invalid email")
        @NotBlank
        String email,
        @NotNull
        LocalDate checkIn,
        @NotNull
        LocalDate checkOut,
        @NotNull
        Integer peopleQuantity,
        @NotNull
        Integer roomsQuantity
) {}