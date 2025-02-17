package gestionDeReservas.Model.dto.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequestDTO {
    String username;
    String password;
    String mail;
    String phoneNumber;
}
