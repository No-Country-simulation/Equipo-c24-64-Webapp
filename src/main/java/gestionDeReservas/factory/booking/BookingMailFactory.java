package gestionDeReservas.factory.booking;

import gestionDeReservas.model.dto.booking.BookingMailDTO;
import gestionDeReservas.model.entity.Booking;
import gestionDeReservas.model.entity.User;
import gestionDeReservas.model.entity.Visitor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookingMailFactory {

    public BookingMailDTO buildBookingMail(Booking booking) {
        Visitor visitor = booking.getVisitor();
        User user = booking.getUser();
        LocalDate currentDate = LocalDate.now();

        return  BookingMailDTO.builder()
                .bookingQuantityRooms(booking.getRooms().size())
                .price(booking.getTotalPrice())
                .priceWithIva(booking.getTotalPriceWithIVA())
                .name(user == null ? visitor.getName() : user.getName())
                .lastname(user == null ? visitor.getLastname() : user.getLastname())
                .email(user == null ? visitor.getEmail() : user.getEmail())
                .bookingDate(currentDate)
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .peopleQuantity(booking.getPeopleQuantity())
                .roomsNumber(booking.getRoomNumbers())
                .specialRequests(booking.getSpecialRequests())
                .build();
    }
}
