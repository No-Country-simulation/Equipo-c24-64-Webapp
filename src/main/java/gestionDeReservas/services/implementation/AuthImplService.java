package gestionDeReservas.services.implementation;

import gestionDeReservas.model.dto.auth.AuthResponseDTO;
import gestionDeReservas.model.dto.auth.LoginRequestDTO;
import gestionDeReservas.model.dto.auth.RegisterRequestDTO;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.config.security.jwt.JwtService;
import gestionDeReservas.exception.LoginException;
import gestionDeReservas.exception.RegisterException;
import gestionDeReservas.factory.UserFactory;
import gestionDeReservas.repository.IUserRepository;
import gestionDeReservas.services.Interface.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthImplService implements AuthService {
    IUserRepository userRepository;
    JwtService jwtService;
    UserFactory userFactory;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO userToRegisterDto) {
        validateRegistration(userToRegisterDto);

        UserEntity user = userFactory.buildUser(userToRegisterDto);

        userRepository.save(user);

        return AuthResponseDTO.builder()
                .username(userToRegisterDto.email())
                .token(jwtService.getToken(user))
                .lastname(user.getLastname())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.email(),
                loginRequestDTO.password()));

        UserEntity user = findUser(loginRequestDTO);

        return AuthResponseDTO.builder()
                    .username(loginRequestDTO.email())
                    .token(jwtService.getToken(user))
                    .role(user.getRole())
                    .lastname(user.getLastname())
                    .name(user.getName())
                    .build();
    }

    @Override
    public void logout(String token) {
        String jwt = token.substring(7);
        jwtService.addToBlacklist(jwt);
    }

    private void validateRegistration(RegisterRequestDTO userToRegisterDto) {
        if (userRepository.existsByEmail(userToRegisterDto.email()))
            throw new RegisterException("the user already exists");
        }

    private UserEntity findUser(LoginRequestDTO loginRequestDTO) {
        return userRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new LoginException("user Not exists"));
    }
}