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

        String price = String.format("$%.2f", bookingMail.price());
        String priceWithIva = String.format("€%.2f", bookingMail.priceWithIva());

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
                <h1 style="color: #4f39f6;">Hola %s %s, ¡Su reserva fue confirmada!</h1>
               \s
                <table>
                    <tr><th>Check-in:</th><td>%s</td></tr>
                    <tr><th>Check-out:</th><td>%s</td></tr>
                    <tr><th>Precio base:</th><td>%s</td></tr>
                    <tr><th>Precio con IVA:</th><td>%s</td></tr>
                    <tr><th>Habitaciones reservadas:</th><td>%s</td></tr>
                </table>
                           \s
                <p style="margin-top: 30px; color: #666;">
                    Gracias por tu reserva. ¡Esperamos que disfrutes tu estancia!
                </p>
            </div>
        </body>
        </html>
       \s""",
                bookingMail.name(),
                bookingMail.lastname(),
                checkIn,
                checkOut,
                price,
                priceWithIva,
                bookingMail.bookingQuantityRooms(),
                rooms);
    }
}