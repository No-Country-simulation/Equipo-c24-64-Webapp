package gestionDeReservas.model.dto.booking;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record BookingMailDTO(
        String email,
        String name,
        String lastname,
        LocalDate checkIn,
        LocalDate checkOut,
        Double price,
        Double priceWithIva,
        Integer bookingQuantityRooms,
        List<Integer> roomsNumber
) {}