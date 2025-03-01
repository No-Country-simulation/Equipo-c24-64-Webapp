package gestionDeReservas.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

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
    UserEntity user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "booking_room",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    List<Room> rooms;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(name = "people_quantity")
    Integer peopleQuantity;

    @Column(name = "rooms_quantity")
    Integer roomsQuantity;

    @Column(name = "check_in")
    LocalDate checkIn;

    @Column(name = "check_out")
    LocalDate checkOut;


    public Integer  getRoomTypeId(){
        return rooms.get(0).getRoomTypeId();
    }
}
