package gestionDeReservas.Model.dto;

public record  RoomCreateRequestDTO(
    String name,
    String description,
    Integer capacity
) {
    
}
