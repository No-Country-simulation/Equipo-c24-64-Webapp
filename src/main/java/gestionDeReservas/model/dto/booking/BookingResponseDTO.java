package gestionDeReservas.model.dto.booking;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record BookingResponseDTO(
        LocalDate bookingDate,
        LocalDate checkIn,
        LocalDate checkOut,
        Double totalPrice,
        Integer roomsQuantity,
        List<Integer> roomsNumber) {
}