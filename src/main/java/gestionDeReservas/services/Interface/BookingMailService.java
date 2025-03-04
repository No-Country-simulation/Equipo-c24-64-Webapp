package gestionDeReservas.services.Interface;

import gestionDeReservas.model.dto.booking.BookingMailDTO;

public interface BookingMailService {
    void sendBookingMail(BookingMailDTO bookingMail);
}
