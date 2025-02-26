package gestionDeReservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestionDeReservas.model.entity.Room;

@Repository
public interface IRoomRepository extends JpaRepository<Room, Integer> {
}