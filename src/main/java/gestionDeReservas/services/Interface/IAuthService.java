package gestionDeReservas.services.Interface;

import gestionDeReservas.model.dto.auth.AuthResponseDTO;
import gestionDeReservas.model.dto.auth.LoginRequestDTO;
import gestionDeReservas.model.dto.auth.RegisterRequestDTO;

public interface IAuthService {
    AuthResponseDTO login (LoginRequestDTO loginRequestDTO);
    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);


     void logout( String token);
}
