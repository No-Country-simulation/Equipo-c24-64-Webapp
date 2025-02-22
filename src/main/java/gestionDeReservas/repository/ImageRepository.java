package gestionDeReservas.repository;

import gestionDeReservas.Model.entity.Image;
import gestionDeReservas.Model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
