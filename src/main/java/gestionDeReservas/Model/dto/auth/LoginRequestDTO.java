package gestionDeReservas.Model.dto.auth;

public record LoginRequestDTO (
        String identifier,
        String password
){
}