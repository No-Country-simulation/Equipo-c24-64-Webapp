package gestionDeReservas.mapper;

import gestionDeReservas.model.dto.booking.BookingResponseDTO;
import gestionDeReservas.model.entity.Booking;
import gestionDeReservas.model.entity.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class BookingMapper {
    public BookingResponseDTO mapBookingToResponseDTO(Booking booking){
        return BookingResponseDTO
                .builder()
                .bookingId(booking.getId())
                .BookingPrice(booking.getTotalPrice())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .numberRooms(getRoomNumbers(booking.getRooms()))
                .build();
    }

    private List<Integer> getRoomNumbers(Set<Room> rooms) {
        List<Integer> numberRooms = new ArrayList<>();
        rooms.forEach(room -> numberRooms.add(room.getRoomNumber()));
        return numberRooms;
    }

    public List<BookingResponseDTO> mapBookingsToResponseListDTO(List<Booking> bookings) {
        return bookings.stream().map(this::mapBookingToResponseDTO).toList();
    }
}
