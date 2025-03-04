package gestionDeReservas.model.dto.booking;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record   BookingMailDTO(
        String email,
        String name,
        String lastname,
        LocalDate bookingDate,
        LocalDate checkIn,
        LocalDate checkOut,
        Double price,
        Double priceWithIva,
        Integer peopleQuantity,
        Integer bookingQuantityRooms,
        List<Integer> roomsNumber,
        String specialRequests
) {}