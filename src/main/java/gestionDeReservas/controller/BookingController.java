package gestionDeReservas.controller;

import gestionDeReservas.model.dto.RoomDTO.EnabledRoomsRequestDTO;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.services.Interface.IBookingService;
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
    IBookingService bookingService;

    @PostMapping("")
    public ResponseEntity<?> saveBooking(@AuthenticationPrincipal UserEntity user,
                                         @RequestBody BookingRequestDTO bookingRequestDTO){
        bookingService.saveBooking(user,bookingRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/enabled-rooms")
    public ResponseEntity<?> saveBooking(@RequestBody EnabledRoomsRequestDTO enabledRoomsRequestDTO){
        return ResponseEntity.ok(bookingService.getEnableRooms(enabledRoomsRequestDTO.idRoomType(),
                enabledRoomsRequestDTO.checkIn(),enabledRoomsRequestDTO.checkOut()));

    }
}