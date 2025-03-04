package gestionDeReservas.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    Integer id;

    @Column(name = "room_number")
    Integer roomNumber;

    @Column(name = "room_name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "capacity")
    Integer capacity;

    @ManyToOne
    @JoinColumn(name = "type_room_id", nullable = false)
    RoomType roomType;

    @ManyToMany(mappedBy = "rooms")
    Set<Booking> bookings;

}