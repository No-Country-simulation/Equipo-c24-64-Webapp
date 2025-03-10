package gestionDeReservas.mapper;

import gestionDeReservas.model.dto.auth.UserResponseDTO;
import gestionDeReservas.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toGetDTO(User user){
        return UserResponseDTO
                .builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .dni(user.getDni())
                .name(user.getName())
                .lastname(user.getLastname())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}