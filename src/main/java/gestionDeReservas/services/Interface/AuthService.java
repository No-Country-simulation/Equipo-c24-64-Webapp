package gestionDeReservas.services.Interface;

import gestionDeReservas.model.dto.auth.*;
import gestionDeReservas.model.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    AuthResponseDTO login (LoginRequestDTO loginRequestDTO);
    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);
    void logout( String token);
    void edit(String email, EditUserRequestDTO editUser);
    UserResponseDTO getData(User user);
}