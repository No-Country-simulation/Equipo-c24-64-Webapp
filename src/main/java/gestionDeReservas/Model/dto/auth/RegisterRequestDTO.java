package gestionDeReservas.Model.dto.auth;

public record RegisterRequestDTO(
        String username,
        String password,
        String mail,
        String phoneNumber,
        String dni) {
}
