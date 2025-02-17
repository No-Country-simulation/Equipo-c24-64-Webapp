package gestionDeReservas.services.impl;

import gestionDeReservas.Model.dto.user.AuthResponseDTO;
import gestionDeReservas.Model.dto.user.LoginRequestDTO;
import gestionDeReservas.Model.dto.user.RegisterRequestDTO;
import gestionDeReservas.Model.entity.UserEntity;
import gestionDeReservas.Model.enums.Role;
import gestionDeReservas.config.security.jwt.JwtService;
import gestionDeReservas.exception.NotFoundException;
import gestionDeReservas.exception.RegisterException;
import gestionDeReservas.repository.IUserRepository;
import gestionDeReservas.services.interfaces.IAuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthImplService implements IAuthService {
    IUserRepository userRepository;
    JwtService jwtService;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmailOrUserName(),
                loginRequestDTO.getPassword()));

        UserEntity user = userRepository.findByUsernameOrEmail(loginRequestDTO.getEmailOrUserName()).orElseThrow(
                () -> new NotFoundException("user with the name: "+loginRequestDTO.getEmailOrUserName()+ " not exists")
        );

        return AuthResponseDTO.builder()
                .username(loginRequestDTO.getEmailOrUserName())
                .token(jwtService.getToken(user))
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO userToRegisterDto) {
        if (userRepository.existsByUsernameOrEmail(userToRegisterDto.getUsername(),
                userToRegisterDto.getMail())) {
            throw new RegisterException("the user have exists: " + userToRegisterDto.getUsername());
        }

        UserEntity user = UserEntity.builder()
                .username(userToRegisterDto.getUsername())
                .password(passwordEncoder.encode(userToRegisterDto.getPassword()))
                .email(userToRegisterDto.getMail())
                .role(Role.CUSTOMER)
                .build();

        userRepository.save(user);

        return AuthResponseDTO.builder()
                .username(userToRegisterDto.getUsername())
                .token(jwtService.getToken(user))
                .role(user.getRole())
                .build();
    }
}
