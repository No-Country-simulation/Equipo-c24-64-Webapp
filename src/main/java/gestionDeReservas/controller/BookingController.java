package gestionDeReservas.controller;

import gestionDeReservas.model.dto.RoomDTO.EnabledRoomsRequestDTO;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.dto.booking.BookingResponseDTO;
import gestionDeReservas.model.dto.booking.EditBookingRequestDTO;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.services.Interface.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BookingController {
    BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> saveBooking(@AuthenticationPrincipal UserEntity user,
                                         @RequestBody BookingRequestDTO bookingRequestDTO){
        bookingService.bookingRooms(user,bookingRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/available-rooms")
    public ResponseEntity<?> saveBooking(@RequestBody EnabledRoomsRequestDTO enabledRoomsRequestDTO){
        return ResponseEntity.ok(bookingService.getAvailableRoomsDTO(enabledRoomsRequestDTO.idRoomType(),
                enabledRoomsRequestDTO.checkIn(),enabledRoomsRequestDTO.checkOut()));

    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable Integer bookingId){
        bookingService.delete(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<BookingResponseDTO>> getBookings(@AuthenticationPrincipal UserEntity user){
        return ResponseEntity.ok(bookingService.getAll(user));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editBooking(@RequestBody EditBookingRequestDTO editBookingRequestDTO){
        bookingService.editBooking(editBookingRequestDTO);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}