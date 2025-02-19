package gestionDeReservas.services.interfaces;

import gestionDeReservas.Model.dto.auth.AuthResponseDTO;
import gestionDeReservas.Model.dto.auth.LoginRequestDTO;
import gestionDeReservas.Model.dto.auth.RegisterRequestDTO;

public interface IAuthService {
    AuthResponseDTO login (LoginRequestDTO loginRequestDTO);
    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);


     void logout( String token);
}
