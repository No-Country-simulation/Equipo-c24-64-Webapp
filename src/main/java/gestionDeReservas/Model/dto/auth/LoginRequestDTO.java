package gestionDeReservas.Model.dto.auth;

public record LoginRequestDTO (
        String emailOrUserName,
        String password){
}