package gestionDeReservas.model.dto.auth;

public record LoginRequestDTO (
        String email,
        String password
){
}