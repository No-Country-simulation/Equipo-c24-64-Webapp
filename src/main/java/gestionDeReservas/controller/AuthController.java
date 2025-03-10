package gestionDeReservas.controller;

import gestionDeReservas.model.dto.auth.EditUserRequestDTO;
import gestionDeReservas.model.dto.auth.LoginRequestDTO;
import gestionDeReservas.model.dto.auth.RegisterRequestDTO;
import gestionDeReservas.model.dto.auth.UserResponseDTO;
import gestionDeReservas.model.entity.User;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authentication", description = "Endpoints for user registration, login and logout")
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "login", description = "Authenticates a user and returns a JWT token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful login "),
            @ApiResponse(responseCode = "401", description = "invalid credentials")
    })
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Create a new user account")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully registered user"),
            @ApiResponse(responseCode = "400", description = "Invalid registration data")
    })
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return new ResponseEntity<>(authService.register(registerRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    @Operation(summary = "Log out", description = "Invalidate the user's JWT token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully Logout"),
            @ApiResponse(responseCode = "403", description = "Invalid or missing token")
    })
    public ResponseEntity<?> logout(
            @Parameter(description = "JWT token in 'Bearer {token}' format", required = true)
            @RequestHeader("Authorization") String token
    ) {
        authService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edition")
    @Operation(summary = "el usuario edita sus datos", description = "el usuario puede editar todos sus datos,menos los mails  y username" +
            "en caso de que ya existan en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully Logout"),
            @ApiResponse(responseCode = "403", description = "Invalid or missing token"),
            @ApiResponse(responseCode = "400", description = "si editas email o username, con un nombre ya utilizado.")
    })
    public ResponseEntity<?> editUser(
            @Parameter(description = "JWT token in 'Bearer {token}' format", required = true)
            @RequestHeader("Authorization")
            @AuthenticationPrincipal User user,
            @RequestBody EditUserRequestDTO editUser
    ){
        authService.edit(user.getEmail(),editUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/data")
    @Operation(summary = "get  data from user", description = "obtener datos del usuario logueado, como username, email,name,etc...")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "data obtenida exitosamente"),
            @ApiResponse(responseCode = "403", description = "Invalid or missing token")
    })
    public ResponseEntity<UserResponseDTO> getUserData(
            @Parameter(description = "JWT token in 'Bearer {token}' format", required = true)
            @RequestHeader("Authorization")
            @AuthenticationPrincipal User user){
        return ResponseEntity.ok(authService.getData(user));
    }
}