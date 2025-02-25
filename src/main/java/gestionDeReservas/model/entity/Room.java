package gestionDeReservas.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer id;

    @Column(name = "room_name")

    private String name;

    @Column(name = "description")

    private String description;

    @Column(name = "capacity")

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "type_room_id", nullable = false)
    private RoomType roomType;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private Set<Booking> bookings;
}