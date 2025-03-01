package gestionDeReservas.services.implementation;

import gestionDeReservas.exception.UserNotFoundException;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.services.Interface.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserImplService implements IUserService {

    @Override
    public UserEntity getUserFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated())
            throw  new UserNotFoundException("Token does not belong to a user");

        return (UserEntity) authentication.getPrincipal();
    }
}
