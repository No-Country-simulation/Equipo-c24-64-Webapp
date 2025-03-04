package gestionDeReservas.services.implementation;

import gestionDeReservas.model.dto.booking.BookingMailDTO;
import gestionDeReservas.services.Interface.BookingMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BookingImplMailService implements BookingMailService {
    JavaMailSender mailSender;

    @Override
    public void sendBookingMail(BookingMailDTO bookingMail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String subject = "succefuly booking";
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(bookingMail.email());
            helper.setSubject(subject);
            helper.setText(getContentEmail(bookingMail), true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
        }
    }

    private String getContentEmail(BookingMailDTO bookingMail) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String checkIn = bookingMail.checkIn().format(dateFormatter);
        String checkOut = bookingMail.checkOut().format(dateFormatter);
        String bookingDate = bookingMail.bookingDate().format(dateFormatter);

        String specialRequests = bookingMail.specialRequests() != null && !bookingMail.specialRequests().isBlank()
                ? bookingMail.specialRequests()
                : "No se han indicado solicitudes especiales.";

        String price = String.format("$%.2f", bookingMail.price());
        String priceWithIva = String.format("$%.2f", bookingMail.priceWithIva());

        String rooms = bookingMail.roomsNumber().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        return String.format("""
        <!DOCTYPE html>
        <html>
        <head>
            <style>
                body { font-family: Arial, sans-serif; }
                .container { max-width: 600px; margin: 20px auto; padding: 20px; }
                table { width: 100%%; border-collapse: collapse; margin: 25px 0; }
                th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
                th { background-color: #155dfc; color: white; }
                .room-numbers { font-weight: bold; color: #155dfc; }
                .special-requests {
                    white-space: pre-wrap;
                    color: #444;
                    font-style: italic;
                    padding: 8px;
                    background-color: #f8f9fa;
                    border-radius: 4px;
                    margin: 5px 0;
                }
                .button {
                    background: #155dfc;\s
                    color: white;\s
                    padding: 12px 24px;
                    text-decoration: none;\s
                    border-radius: 5px;
                    display: inline-block;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <h1 style="color: #155dfc;">Hola %s %s, ¡Su reserva fue confirmada!</h1>
                
                <table>
                    <tr><th>Fecha de la reserva:</th><td>%s</td></tr>                
                    <tr><th>Check-in:</th><td>%s</td></tr>
                    <tr><th>Check-out:</th><td>%s</td></tr>
                    <tr><th>Precio base:</th><td>%s</td></tr>
                    <tr><th>Precio con IVA:</th><td>%s</td></tr>
                    <tr><th>cantidad de personas:</th><td>%s</td></tr>
                    <tr><th>Cantidad de habitaciones:</th><td>%d</td></tr>
                    <tr><th>Números de habitación:</th><td><span class="room-numbers">%s</span></td></tr>
                    <tr><th>Solicitudes especiales:</th><td><div class="special-requests">%s</div></td></tr>
                </table>
                
                <p style="margin-top: 30px; color: #666;">
                    Gracias por tu reserva. ¡Esperamos que disfrutes tu estancia!
                </p>
            </div>
        </body>
        </html>
        """,
                bookingMail.name(),
                bookingMail.lastname(),
                bookingDate,
                checkIn,
                checkOut,
                price,
                priceWithIva,
                bookingMail.peopleQuantity(),
                bookingMail.bookingQuantityRooms(),
                rooms,
                specialRequests);
    }
}