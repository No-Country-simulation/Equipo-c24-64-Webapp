package gestionDeReservas.controller;

import gestionDeReservas.model.dto.auth.LoginRequestDTO;
import gestionDeReservas.model.dto.auth.RegisterRequestDTO;
import gestionDeReservas.services.Interface.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authentication", description = "Endpoints para registro, login y logout de usuarios")
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "login", description = "Autentica un usuario y retorna un token JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful login "),
            @ApiResponse(responseCode = "401", description = "invalid credentials")
    })
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar usuario", description = "Crea una nueva cuenta de usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de registro inválidos")
    })
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) { // Corrige el nombre del método (error tipográfico)
        return new ResponseEntity<>(authService.register(registerRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    @Operation(summary = "Cerrar sesión", description = "Invalida el token JWT del usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Logout exitoso"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    public ResponseEntity<?> logout(
            @Parameter(description = "Token JWT en formato 'Bearer {token}'", required = true)
            @RequestHeader("Authorization") String token
    ) {
        authService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}