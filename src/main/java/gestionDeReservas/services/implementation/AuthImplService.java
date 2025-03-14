package gestionDeReservas.services.implementation;

import gestionDeReservas.exception.BadRequestException;
import gestionDeReservas.exception.NotFoundException;
import gestionDeReservas.mapper.UserMapper;
import gestionDeReservas.model.dto.auth.*;
import gestionDeReservas.model.entity.User;
import gestionDeReservas.config.security.jwt.JwtService;
import gestionDeReservas.exception.RegisterException;
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
    UserMapper userMapper;
    AuthenticationManager authenticationManager;
    JwtService jwtService;

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.identifier(),
                        loginRequestDTO.password()));

        User user = findUser(loginRequestDTO);
        return userMapper.buildResponseAuthDTO(user);
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO userToRegisterDTO) {
        validateRegistration(userToRegisterDTO);

        User user = userMapper.buildUser(userToRegisterDTO);
        userRepository.save(user);

        return userMapper.buildResponseAuthDTO(user);
    }

    @Override
    public void logout(String token) {
        String jwt = token.substring(7);
        jwtService.addToBlacklist(jwt);
    }

    @Override
    public void edit(String email, EditUserRequestDTO editUserRequestDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("user with email: "+email +" not founded"));

        validateNewUsernameAndEmail(editUserRequestDTO);

        User editedUser = userMapper.toEditUser(editUserRequestDTO,user);

        userRepository.save(editedUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    private void validateNewUsernameAndEmail(EditUserRequestDTO editUser) {
        if(userRepository.existsByEmailOrUsername(editUser.email(), editUser.username())){
            throw new BadRequestException("email or username already exists");
        }
    }

    private void validateRegistration(RegisterRequestDTO userToRegisterDTO) {
        if (userRepository.existsByEmailOrUsername(userToRegisterDTO.email(),
        userToRegisterDTO.username()))
            throw new RegisterException("the user already exists");
    }

    private User findUser(LoginRequestDTO loginRequestDTO) {
        return userRepository.findByUsernameOrEmail(loginRequestDTO.identifier())
                .orElseThrow(() -> new BadRequestException("user Not exists"));
    }
}