package gestionDeReservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestionDeReservas.Model.entity.Room;
import gestionDeReservas.Model.entity.RoomType;

public interface TypeRoomRepository  extends JpaRepository<RoomType, Integer>{
    
}
