package gestionDeReservas.factory.booking;

import gestionDeReservas.model.dto.booking.BookingMailDTO;
import gestionDeReservas.model.entity.Booking;
import gestionDeReservas.model.entity.UserEntity;
import gestionDeReservas.model.entity.Visitor;
import org.springframework.stereotype.Component;

@Component
public class BookingMailFactory {

    public BookingMailDTO buildBookingMail(Booking booking) {
        Visitor visitor = booking.getVisitor();
        UserEntity user = booking.getUserEntity();

        BookingMailDTO dto = BookingMailDTO.builder()
                .bookingQuantityRooms(booking.getPeopleQuantity())
                .price(booking.getTotalPrice())
                .priceWithIva(booking.getTotalPriceWithIVA())
                .name(user == null ? visitor.getName() : user.getName())
                .lastname(user == null ? visitor.getLastname() : user.getLastname())
                .email(user == null ? visitor.getEmail() : user.getEmail())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .roomsNumber(booking.getRoomNumbers())
                .build();

        System.out.println("\n=== DEBUG BookingMailDTO Values ===");
        System.out.println("Email: " + dto.email());
        System.out.println("Nombre: " + dto.name());
        System.out.println("Apellido: " + dto.lastname());
        System.out.println("Check-in: " + (dto.checkIn() != null ? dto.checkIn() : "null"));
        System.out.println("Check-out: " + (dto.checkOut() != null ? dto.checkOut() : "null"));
        System.out.println("Precio: " + (dto.price() != null ? dto.price() : "null"));
        System.out.println("Precio con IVA: " + (dto.priceWithIva() != null ? dto.priceWithIva() : "null"));
        System.out.println("Cantidad de habitaciones: " + (dto.bookingQuantityRooms() != null ? dto.bookingQuantityRooms() : "null"));

        if (dto.roomsNumber() != null && !dto.roomsNumber().isEmpty()) {
            System.out.println("Números de habitación: " + dto.roomsNumber());
        } else {
            System.out.println("Números de habitación: null o vacío");
        }

        System.out.println("===================================\n");

        return dto;
    }
}
