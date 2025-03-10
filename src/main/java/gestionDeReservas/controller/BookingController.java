package gestionDeReservas.controller;

import gestionDeReservas.model.dto.RoomDTO.EnabledRoomsRequestDTO;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.User;
import gestionDeReservas.services.Interface.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BookingController {
    BookingService bookingService;

    @PostMapping
    @Operation(summary = "save booking", description = "Create a new booking")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully registered booking"),
            @ApiResponse(responseCode = "400", description = "Invalid booking data, for example: range of the dates")
    })
    public ResponseEntity<?> saveBooking(@RequestBody BookingRequestDTO bookingRequestDTO) throws Exception {
        bookingService.bookingRooms(bookingRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user")
    @Operation(summary = "get bookings from the user", description = "obtenes todos las reservas de los usuarios que esten registrados y logueados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully bookings getting"),
    })
    public  ResponseEntity<?> getBookingsFromUser(
            @Parameter(description = "JWT token in 'Bearer {token}' format", required = true)
            @RequestHeader("Authorization")
            @AuthenticationPrincipal User user){
        return ResponseEntity.ok(bookingService.getBookingsFromuser(user.getEmail()));
    }

    @GetMapping("/available-rooms")
    @Operation(summary = "get available rooms", description = "obtener habitaciones disponibles para reservar, en un rango de fechas (check-in y check-out)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "se obtiene con exito las habitaciones disponibles"),
    })
    public ResponseEntity<?> getAvailableRooms(@RequestBody EnabledRoomsRequestDTO enabledRoomsRequestDTO) throws Exception{
        return ResponseEntity.ok(bookingService.getAvailableRoomsDTO(enabledRoomsRequestDTO.idRoomType(),
                enabledRoomsRequestDTO.checkIn(),enabledRoomsRequestDTO.checkOut()));
    }
}