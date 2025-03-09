package gestionDeReservas.factory.auth;

import gestionDeReservas.model.dto.auth.RegisterRequestDTO;
import gestionDeReservas.model.entity.User;
import gestionDeReservas.model.enums.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserFactory {
    PasswordEncoder passwordEncoder;

    public User buildUser(RegisterRequestDTO registerRequestDTO){
        return User
                .builder()
                .username(registerRequestDTO.username())
                .email(registerRequestDTO.email())
                .name(registerRequestDTO.name())
                .lastname(registerRequestDTO.lastname())
                .address(registerRequestDTO.address())
                .phoneNumber(registerRequestDTO.phoneNumber())
                .dni(registerRequestDTO.dni())
                .password(passwordEncoder.encode(registerRequestDTO.password()))
                .role(Role.CUSTOMER)
                .build();
    }
}
