package gestionDeReservas.services.Interface;

import gestionDeReservas.model.dto.auth.*;

public interface AuthService {
    AuthResponseDTO login (LoginRequestDTO loginRequestDTO);
    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);
    void logout( String token);
    void edit(String email, EditUserRequestDTO editUser);
}