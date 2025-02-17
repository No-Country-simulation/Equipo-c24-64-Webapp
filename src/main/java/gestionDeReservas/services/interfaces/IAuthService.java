package gestionDeReservas.services.interfaces;

import gestionDeReservas.Model.dto.user.AuthResponseDTO;
import gestionDeReservas.Model.dto.user.LoginRequestDTO;
import gestionDeReservas.Model.dto.user.RegisterRequestDTO;

public interface IAuthService {
    AuthResponseDTO login (LoginRequestDTO loginRequestDTO);
    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);
}
