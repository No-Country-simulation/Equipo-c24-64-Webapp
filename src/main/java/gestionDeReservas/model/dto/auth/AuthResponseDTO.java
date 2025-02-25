package gestionDeReservas.model.dto.auth;
import gestionDeReservas.model.enums.Role;
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