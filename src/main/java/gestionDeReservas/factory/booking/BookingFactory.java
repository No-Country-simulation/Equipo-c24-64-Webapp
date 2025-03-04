package gestionDeReservas.factory.booking;

import gestionDeReservas.exception.NotRoomFoundException;
import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.*;
import gestionDeReservas.repository.IRoomTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingFactory {
    IRoomTypeRepository roomTypeRepository;
    Double IVA = 0.21;

    public Booking buildBooking(BookingRequestDTO bookingRequestDTO, UserEntity user, List<Room> rooms) {
        RoomType roomType = roomTypeRepository.findById(bookingRequestDTO.idRoomType())
                .orElseThrow(() -> new NotRoomFoundException("typeRoom not founded"));

        Long stayDuration = calculateHotelStayDuration(bookingRequestDTO.checkIn(), bookingRequestDTO.checkOut());
        Double bookingPrice = roomType.getPrice() * stayDuration * rooms.size();
        Double bookingPriceWithIVA = bookingPrice + (bookingPrice*IVA);

        return Booking.builder()
                .checkIn(bookingRequestDTO.checkIn())
                .checkOut(bookingRequestDTO.checkOut())
                .totalPrice(bookingPrice)
                .totalPriceWithIVA(bookingPriceWithIVA)
                .peopleQuantity(bookingRequestDTO.peopleQuantity())
                .rooms(new HashSet<>(rooms))
                .userEntity(user)
                .specialRequests(bookingRequestDTO.specialRequests())
                .build();
    }

    public Booking buildVisitorBooking(BookingRequestDTO bookingRequestDTO, Visitor visitor, List<Room> rooms) {
        RoomType roomType = roomTypeRepository.findById(bookingRequestDTO.idRoomType())
                .orElseThrow(() -> new NotRoomFoundException("typeRoom not founded"));

        Long stayDuration = calculateHotelStayDuration(bookingRequestDTO.checkIn(), bookingRequestDTO.checkOut());
        Double bookingPrice = roomType.getPrice() * stayDuration * rooms.size();
        Double bookingPriceWithIVA = bookingPrice + (bookingPrice*IVA);

        return Booking.builder()
                .checkIn(bookingRequestDTO.checkIn())
                .checkOut(bookingRequestDTO.checkOut())
                .totalPrice(bookingPrice)
                .totalPriceWithIVA(bookingPriceWithIVA)
                .peopleQuantity(bookingRequestDTO.peopleQuantity())
                .rooms(new HashSet<>(rooms))
                .visitor(visitor)
                .specialRequests(bookingRequestDTO.specialRequests())
                .build();
    }

    private Long calculateHotelStayDuration(LocalDate checkInDate, LocalDate checkOutDate) {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate) + 1;
    }
}