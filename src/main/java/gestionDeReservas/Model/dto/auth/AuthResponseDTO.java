package gestionDeReservas.Model.dto.auth;
import gestionDeReservas.Model.enums.Role;
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