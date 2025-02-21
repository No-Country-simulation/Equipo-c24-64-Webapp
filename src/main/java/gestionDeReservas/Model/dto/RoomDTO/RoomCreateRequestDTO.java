package gestionDeReservas.Model.dto.RoomDTO;


public record  RoomCreateRequestDTO(
    String name,
    String description,
    Integer capacity,
    Integer typeRoomID
) {
    
}
