package gestionDeReservas.model.dto.visitor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record VisitorRequestDTO(
        @Email(message = "invalid email")
        String email,
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String dni
) {
}
