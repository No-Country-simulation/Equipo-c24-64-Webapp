package gestionDeReservas.controller;

import gestionDeReservas.model.dto.RoomDTO.EnabledRoomsRequestDTO;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.dto.booking.VisitorBookingRequestDTO;
import gestionDeReservas.model.entity.UserEntity;
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
    public ResponseEntity<?> saveBooking(@RequestBody BookingRequestDTO bookingRequestDTO) throws Exception {
        bookingService.bookingRooms(bookingRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/enabled-rooms")
    public ResponseEntity<?> saveBooking(@RequestBody EnabledRoomsRequestDTO enabledRoomsRequestDTO) throws Exception{
        return ResponseEntity.ok(bookingService.getAvailableRoomsDTO(enabledRoomsRequestDTO.idRoomType(),
                enabledRoomsRequestDTO.checkIn(),enabledRoomsRequestDTO.checkOut()));
    }
}