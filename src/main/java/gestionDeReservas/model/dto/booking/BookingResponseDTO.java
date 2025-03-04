package gestionDeReservas.model.dto.booking;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookingResponseDTO(
        Double BookingPrice,
        LocalDate checkIn,
        LocalDate checkOut

){}