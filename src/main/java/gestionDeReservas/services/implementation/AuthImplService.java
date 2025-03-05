package gestionDeReservas.services.implementation;

import gestionDeReservas.exception.BadRequestException;
import gestionDeReservas.model.dto.auth.AuthResponseDTO;
import gestionDeReservas.model.dto.auth.LoginRequestDTO;
import gestionDeReservas.model.dto.auth.RegisterRequestDTO;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.config.security.jwt.JwtService;
import gestionDeReservas.exception.RegisterException;
import gestionDeReservas.factory.auth.AuthResponseDTOFactory;
import gestionDeReservas.factory.auth.UserFactory;
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
    UserFactory userFactory;
    AuthenticationManager authenticationManager;
    AuthResponseDTOFactory authResponseFactory;
    JwtService jwtService;

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.identifier(),
                        loginRequestDTO.password()));

        UserEntity user = findUser(loginRequestDTO);

        return authResponseFactory.buildResponseAuthDTO(user);
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO userToRegisterDTO) {
        validateRegistration(userToRegisterDTO);

        UserEntity user = userFactory.buildUser(userToRegisterDTO);

        userRepository.save(user);

        return authResponseFactory.buildResponseAuthDTO(user);
    }

    @Override
    public void logout(String token) {
        String jwt = token.substring(7);
        jwtService.addToBlacklist(jwt);
    }

    private void validateRegistration(RegisterRequestDTO userToRegisterDTO) {
        if (userRepository.existsByEmailOrUsername(userToRegisterDTO.email(),
        userToRegisterDTO.username()))
            throw new RegisterException("the user already exists");
    }

    private UserEntity findUser(LoginRequestDTO loginRequestDTO) {
        return userRepository.findByUsernameOrEmail(loginRequestDTO.identifier())
                .orElseThrow(() -> new BadRequestException("user Not exists"));
    }
}