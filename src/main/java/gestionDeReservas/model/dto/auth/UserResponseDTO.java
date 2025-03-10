package gestionDeReservas.model.dto.auth;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        String username,
        String address,
        String name,
        String lastname,
        String email,
        String phoneNumber,
        String dni
) {
}
