package gestionDeReservas.factory;

import gestionDeReservas.model.dto.auth.RegisterRequestDTO;
import gestionDeReservas.model.entity.UserEntity;
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

    public UserEntity buildUser(RegisterRequestDTO registerRequestDTO){
        return UserEntity
                .builder()
                .name(registerRequestDTO.name())
                .email(registerRequestDTO.email())
                .lastname(registerRequestDTO.lastname())
                .address(registerRequestDTO.address())
                .numberPhone(registerRequestDTO.phoneNumber())
                .dni(registerRequestDTO.dni())
                .password(passwordEncoder.encode(registerRequestDTO.password()))
                .role(Role.CUSTOMER)
                .build();
    }
}
