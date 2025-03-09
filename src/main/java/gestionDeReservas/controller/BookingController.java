package gestionDeReservas.controller;

import gestionDeReservas.model.dto.RoomDTO.EnabledRoomsRequestDTO;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.User;
import gestionDeReservas.services.Interface.BookingService;
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
    public ResponseEntity<?> saveBooking(@RequestBody BookingRequestDTO bookingRequestDTO){
        bookingService.bookingRooms(bookingRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public  ResponseEntity<?> getBookingsFromUser(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(bookingService.getBookingsFromuser(user.getEmail()));
    }

    @GetMapping("/enabled-rooms")
    public ResponseEntity<?> saveBooking(@RequestBody EnabledRoomsRequestDTO enabledRoomsRequestDTO){
        return ResponseEntity.ok(bookingService.getAvailableRoomsDTO(enabledRoomsRequestDTO.idRoomType(),
                enabledRoomsRequestDTO.checkIn(),enabledRoomsRequestDTO.checkOut()));
    }
}