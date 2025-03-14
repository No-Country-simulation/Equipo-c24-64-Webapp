    package gestionDeReservas.factory;

    import gestionDeReservas.model.dto.booking.BookingRequestDTO;
    import gestionDeReservas.model.entity.*;
    import gestionDeReservas.services.Interface.RoomTypeServiceUI;
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
        RoomTypeServiceUI roomTypeService;
        Double IVA = 0.21;

        public Booking buildBooking(BookingRequestDTO bookingRequestDTO, User user,Visitor visitor, List<Room> rooms) {
            RoomType roomType = roomTypeService.getRoomTypeById(bookingRequestDTO.idRoomType());

            Long stayDuration = calculateHotelStayDuration(bookingRequestDTO.checkIn(), bookingRequestDTO.checkOut());
            Double bookingPrice = roomType.getPrice() * stayDuration * rooms.size();
            Double bookingPriceWithIVA = bookingPrice + (bookingPrice*IVA);

            return Booking.builder()
                    .bookingDate(LocalDate.now())
                    .checkIn(bookingRequestDTO.checkIn())
                    .checkOut(bookingRequestDTO.checkOut())
                    .totalPrice(bookingPrice)
                    .totalPriceWithIVA(bookingPriceWithIVA)
                    .peopleQuantity(bookingRequestDTO.peopleQuantity())
                    .rooms(new HashSet<>(rooms))
                    .user(user)
                    .visitor(visitor)
                    .specialRequests(bookingRequestDTO.specialRequests())
                    .build();
        }

        private Long calculateHotelStayDuration(LocalDate checkInDate, LocalDate checkOutDate) {
            return ChronoUnit.DAYS.between(checkInDate, checkOutDate) + 1;
        }
    }