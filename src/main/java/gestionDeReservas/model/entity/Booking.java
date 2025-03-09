package gestionDeReservas.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_id")
    Visitor visitor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "booking_room",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    Set<Room> rooms;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(name = "total_price_IVA")
    Double totalPriceWithIVA;

    @Column(name = "people_quantity")
    Integer peopleQuantity;

    @Column(name = "booking_date")
    LocalDate bookingDate;

    @Column(name = "check_in")
    LocalDate checkIn;

    @Column(name = "check_out")
    LocalDate checkOut;

    @Column(name = "special_requests")
    String specialRequests;

    public List<Integer> getRoomNumbers() {
        return rooms.stream()
                .map(Room::getRoomNumber)
                .sorted()
                .collect(Collectors.toList());
    }
}