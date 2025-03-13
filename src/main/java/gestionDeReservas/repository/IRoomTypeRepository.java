package gestionDeReservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionDeReservas.model.entity.RoomType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IRoomTypeRepository extends JpaRepository<RoomType, Integer>{
}