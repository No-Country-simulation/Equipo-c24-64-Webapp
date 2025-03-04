package gestionDeReservas.model.dto.RoomDTO;

public record  RoomCreateRequestDTO(
    String name,
    String description,
    Integer roomNumber,
    Integer typeRoomID
) {
}