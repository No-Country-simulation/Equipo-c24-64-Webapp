package gestionDeReservas.services.Interface;

import gestionDeReservas.model.dto.booking.BookingRequestDTO;
import gestionDeReservas.model.entity.UserEntity;

public interface IBookingService {
    public void saveBooking(UserEntity user, BookingRequestDTO bookingRequestDTO) ;
}
