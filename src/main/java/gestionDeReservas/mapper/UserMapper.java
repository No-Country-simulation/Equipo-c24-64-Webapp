package gestionDeReservas.mapper;

import gestionDeReservas.config.security.jwt.JwtService;
import gestionDeReservas.enums.Role;
import gestionDeReservas.model.dto.auth.AuthResponseDTO;
import gestionDeReservas.model.dto.auth.EditUserRequestDTO;
import gestionDeReservas.model.dto.auth.RegisterRequestDTO;
import gestionDeReservas.model.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserMapper {
    PasswordEncoder passwordEncoder;
    JwtService jwtService;

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

    public AuthResponseDTO buildResponseAuthDTO(User user){
        return AuthResponseDTO
                .builder()
                .email(user.getEmail())
                .name(user.getName())
                .lastname(user.getLastname())
                .role(Role.CUSTOMER)
                .token(jwtService.getToken(user))
                .username(user.getUsername())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public User toEditUser(EditUserRequestDTO editUser, User user) {
        user.setEmail(editUser.email() != null ? editUser.email() : user.getEmail());
        user.setUsername(editUser.username() != null ? editUser.username() : user.getUsername());
        user.setPassword(editUser.password() != null ? passwordEncoder.encode(editUser.password()) :
                passwordEncoder.encode(user.getPassword()));
        user.setName(editUser.name() != null ? editUser.name() : user.getName());
        user.setLastname(editUser.lastname() != null ? editUser.lastname() : user.getLastname());
        user.setAddress(editUser.address() != null ? editUser.address() : user.getAddress());
        user.setPhoneNumber(editUser.phoneNumber() != null ? editUser.phoneNumber() : user.getPhoneNumber());
        user.setDni(editUser.dni() != null ? editUser.dni() : user.getDni());

        return user;
    }
}