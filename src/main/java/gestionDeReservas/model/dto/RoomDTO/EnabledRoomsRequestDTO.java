package gestionDeReservas.model.dto.RoomDTO;

import java.time.LocalDate;

public record EnabledRoomsRequestDTO(
        Integer idRoomType,
        LocalDate checkIn,
        LocalDate checkOut
) {
}
