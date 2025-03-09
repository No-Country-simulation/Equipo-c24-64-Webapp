package gestionDeReservas.factory.auth;

import gestionDeReservas.enums.Role;
import gestionDeReservas.model.dto.auth.AuthResponseDTO;
import gestionDeReservas.model.entity.User;
import gestionDeReservas.config.security.jwt.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthResponseDTOFactory {
    JwtService jwtService;

    public AuthResponseDTO buildResponseAuthDTO(User user){
        return AuthResponseDTO
                .builder()
                .email(user.getEmail())
                .name(user.getName())
                .lastname(user.getLastname())
                .role(Role.CUSTOMER)
                .token(jwtService.getToken(user))
                .username(user.getUsername())
                .build();
    }
}