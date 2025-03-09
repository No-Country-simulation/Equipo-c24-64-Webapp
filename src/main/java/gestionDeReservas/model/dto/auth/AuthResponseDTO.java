package gestionDeReservas.model.dto.auth;
import gestionDeReservas.enums.Role;
import lombok.Builder;

@Builder
public record AuthResponseDTO(
        String email,
        String username,
        String token,
        Role role,
        String name,
        String lastname) {
}