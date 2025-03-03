package gestionDeReservas.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserEntity userEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "booking_room",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    Set<Room> rooms;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(name = "people_quantity")
    Integer peopleQuantity;

    @Column(name = "check_in")
    LocalDate checkIn;

    @Column(name = "check_out")
    LocalDate checkOut;
}
