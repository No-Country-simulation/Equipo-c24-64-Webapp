package gestionDeReservas.Model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Image {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "public_id", nullable = false)
    private String publicId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
