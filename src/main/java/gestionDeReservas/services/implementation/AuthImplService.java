package gestionDeReservas.services.implementation;

import gestionDeReservas.Model.dto.auth.AuthResponseDTO;
import gestionDeReservas.Model.dto.auth.LoginRequestDTO;
import gestionDeReservas.Model.dto.auth.RegisterRequestDTO;
import gestionDeReservas.Model.entity.UserEntity;
import gestionDeReservas.config.security.jwt.JwtService;
import gestionDeReservas.exception.NotFoundException;
import gestionDeReservas.exception.RegisterException;
import gestionDeReservas.factory.UserFactory;
import gestionDeReservas.repository.IUserRepository;
import gestionDeReservas.services.interfaces.IAuthService;
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
    JwtService jwtService;
    UserFactory userFactory;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO userToRegisterDto) {
        validateRegistration(userToRegisterDto);

        UserEntity user = userFactory.buildUser(userToRegisterDto);

        userRepository.save(user);

        return AuthResponseDTO.builder()
                .username(userToRegisterDto.username())
                .token(jwtService.getToken(user))
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.emailOrUserName(),
                loginRequestDTO.password()));

        UserEntity user = findUser(loginRequestDTO);

        return AuthResponseDTO.builder()
                .username(loginRequestDTO.emailOrUserName())
                .token(jwtService.getToken(user))
                .role(user.getRole())
                .build();
    }

    private void validateRegistration(RegisterRequestDTO userToRegisterDto) {
        if (userRepository.existsByUsernameOrEmail(userToRegisterDto.username(),
                userToRegisterDto.mail())) {
            throw new RegisterException("the user already exists");
        }
    }

    private UserEntity findUser(LoginRequestDTO loginRequestDTO){
        return userRepository.findByUsernameOrEmail(loginRequestDTO.emailOrUserName())
                .orElseThrow(
                        () -> new NotFoundException("user with the name: "
                                +loginRequestDTO.emailOrUserName()+ " not exists"));
    }
}
