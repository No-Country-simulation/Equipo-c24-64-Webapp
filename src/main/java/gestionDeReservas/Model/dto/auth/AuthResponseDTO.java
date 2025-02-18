package gestionDeReservas.Model.dto.auth;
import gestionDeReservas.Model.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
public record AuthResponseDTO(String username,
        String token,
        Role role) {
}
