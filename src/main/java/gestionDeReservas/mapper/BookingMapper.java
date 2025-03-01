package gestionDeReservas.mapper;

import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.dto.booking.BookingResponseDTO;
import gestionDeReservas.model.dto.booking.EditBookingRequestDTO;
import gestionDeReservas.model.entity.Booking;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.UserEntity;
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

    private List<Integer> getRoomNumbers(List<Room> rooms) {
        List<Integer> numberRooms = new ArrayList<>();
        rooms.forEach(room -> numberRooms.add(room.getRoomNumber()));
        return numberRooms;
    }

    public List<BookingResponseDTO> mapBookingsToResponseListDTO(List<Booking> bookings) {
        return bookings.stream().map(this::mapBookingToResponseDTO).toList();
    }

    public BookingRequestDTO mapBookingToBookingRequest(Booking booking, EditBookingRequestDTO editBookingRequestDTO, UserEntity user){
        return BookingRequestDTO
                .builder()
                .idRoomType(editBookingRequestDTO.idRoomType().equals(booking.getRoomTypeId())? booking.getRoomTypeId() : editBookingRequestDTO.idRoomType())
                .peopleQuantity(editBookingRequestDTO.peopleQuantity().equals(booking.getPeopleQuantity()) ? booking.getPeopleQuantity() : editBookingRequestDTO.peopleQuantity())
                .roomsQuantity(editBookingRequestDTO.roomsQuantity().equals(booking.getRoomsQuantity()) ? booking.getRoomsQuantity() : editBookingRequestDTO.roomsQuantity() )
                .checkIn(editBookingRequestDTO.checkIn().equals(booking.getCheckIn())? booking.getCheckIn() : editBookingRequestDTO.checkIn())
                .checkOut(editBookingRequestDTO.checkOut().equals(booking.getCheckOut())? booking.getCheckOut() : editBookingRequestDTO.checkOut())
                .build();
    }
}
