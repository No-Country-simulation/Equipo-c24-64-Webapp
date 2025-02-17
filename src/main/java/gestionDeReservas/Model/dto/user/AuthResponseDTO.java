package gestionDeReservas.Model.dto.user;
import gestionDeReservas.Model.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponseDTO {
    String username;
    String token;
    Role role;
}
