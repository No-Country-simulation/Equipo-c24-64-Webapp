package gestionDeReservas.Model.dto.auth;

public record LoginRequestDTO (
        String email,
        String password
){
}