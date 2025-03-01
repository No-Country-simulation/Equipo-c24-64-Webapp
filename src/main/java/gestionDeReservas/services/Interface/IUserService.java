package gestionDeReservas.services.Interface;

import gestionDeReservas.model.entity.UserEntity;

public interface IUserService {
    UserEntity getUserFromToken();
}
