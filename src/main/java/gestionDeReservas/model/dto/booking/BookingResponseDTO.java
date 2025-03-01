package gestionDeReservas.model.dto.booking;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
public record BookingResponseDTO(
        Integer bookingId,
        Double BookingPrice,
        LocalDate checkIn,
        LocalDate checkOut,
        List<Integer> numberRooms
)
{}