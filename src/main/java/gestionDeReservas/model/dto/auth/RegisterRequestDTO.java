package gestionDeReservas.model.dto.auth;

public record RegisterRequestDTO(
          String email,
          String username,
          String password,
          String name,
          String lastname,
          String address,
          String phoneNumber,
          String dni
) {
}