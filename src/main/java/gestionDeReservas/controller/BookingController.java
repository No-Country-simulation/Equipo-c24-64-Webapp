package gestionDeReservas.controller;

import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.services.Interface.IBookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
