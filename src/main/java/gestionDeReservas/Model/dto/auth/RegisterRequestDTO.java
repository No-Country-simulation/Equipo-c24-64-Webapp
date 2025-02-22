package gestionDeReservas.Model.dto.auth;

public record RegisterRequestDTO(
        String email,
        String password,
        String name,
        String lastname,
        String address,
        String phoneNumber,
        String dni) {
}
