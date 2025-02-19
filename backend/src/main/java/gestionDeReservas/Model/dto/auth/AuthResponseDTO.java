package gestionDeReservas.Model.dto.auth;
import gestionDeReservas.Model.enums.Role;
import lombok.Builder;

@Builder
public record AuthResponseDTO(
        String username,
        String token,
        Role role) {
}
