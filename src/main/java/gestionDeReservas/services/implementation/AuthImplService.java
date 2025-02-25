package gestionDeReservas.services.implementation;

import gestionDeReservas.Model.dto.auth.AuthResponseDTO;
import gestionDeReservas.Model.dto.auth.LoginRequestDTO;
import gestionDeReservas.Model.dto.auth.RegisterRequestDTO;
import gestionDeReservas.Model.entity.UserEntity;
import gestionDeReservas.config.security.jwt.JwtService;
import gestionDeReservas.exception.LoginException;
import gestionDeReservas.exception.RegisterException;
import gestionDeReservas.factory.auth.AuthResponseDTOFactory;
import gestionDeReservas.factory.auth.UserFactory;
import gestionDeReservas.repository.IUserRepository;
import gestionDeReservas.services.Interface.IAuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthImplService implements IAuthService {
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
    public AuthResponseDTO register(RegisterRequestDTO userToRegisterDto) {
        validateRegistration(userToRegisterDto);

        UserEntity user = userFactory.buildUser(userToRegisterDto);

        userRepository.save(user);

        return authResponseFactory.buildResponseAuthDTO(user);
    }

    @Override
    public void logout(String token) {
        String jwt = token.substring(7);
        jwtService.addToBlacklist(jwt);
    }

    private void validateRegistration(RegisterRequestDTO userToRegisterDto) {
        if (userRepository.existsByEmailOrUsername(userToRegisterDto.email(),
        userToRegisterDto.username()))
            throw new RegisterException("the user already exists");
    }

    private UserEntity findUser(LoginRequestDTO loginRequestDTO) {
        return userRepository.findByUsernameOrEmail(loginRequestDTO.identifier())
                .orElseThrow(() -> new LoginException("user Not exists"));
    }
}