package gestionDeReservas.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "visitor", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
    Set<Booking> bookings;

    String email;
    String name;
    String lastname;
    @Column(name = "phone_number")
    String phoneNumber;
    String dni;
}