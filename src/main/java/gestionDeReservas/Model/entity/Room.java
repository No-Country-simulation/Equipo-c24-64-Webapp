package gestionDeReservas.Model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "room_name", nullable = false)
    @Min(2)
    @Max(100)
    private String name;

    @Column(name = "description")
    @Min(2)
    @Max(100)
    private String description;

    @Column(name = "capacity",  nullable = false)
    @Min(1)
    @Max(7)
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "type_room_id", nullable = false)
    private RoomType roomType;
}
