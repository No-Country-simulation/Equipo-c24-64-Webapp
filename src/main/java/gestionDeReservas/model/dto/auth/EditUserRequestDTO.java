package gestionDeReservas.model.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EditUserRequestDTO(
        @Email(message = "the email format is invalid")
        @NotBlank
        String email,
        String username,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
                message = "The password must be at least 8 characters, a capital letter and a number")
        String password,
        String name,
        String lastname,
        String address,
        String phoneNumber,
        String dni
) {
}