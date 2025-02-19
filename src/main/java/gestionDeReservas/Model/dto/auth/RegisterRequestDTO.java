package gestionDeReservas.Model.dto.auth;

public record RegisterRequestDTO(
        String email,
        String password,
        String name,
        String lastName,
        String address,
        String phoneNumber,
        String dni) {
}
