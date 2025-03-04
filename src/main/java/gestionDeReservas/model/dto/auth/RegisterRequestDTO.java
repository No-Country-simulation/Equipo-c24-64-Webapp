package gestionDeReservas.model.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterRequestDTO(
        @Email(message = "the email format is invalid")
        @NotBlank
        String email,
        @NotBlank
        String username,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
                message = "The password must be at least 8 characters, a capital letter and a number")
        @NotBlank(message = "the password can not be empty")
        String password,
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotBlank
        String address,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String dni
) {
}