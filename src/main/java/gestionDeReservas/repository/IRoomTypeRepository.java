package gestionDeReservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionDeReservas.model.entity.RoomType;

public interface IRoomTypeRepository extends JpaRepository<RoomType, Integer>{
    
}
