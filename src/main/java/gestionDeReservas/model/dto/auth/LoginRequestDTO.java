package gestionDeReservas.model.dto.auth;

public record LoginRequestDTO (
        String identifier,
        String password
){
}