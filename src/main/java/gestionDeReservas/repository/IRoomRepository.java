package gestionDeReservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gestionDeReservas.model.entity.Room;

import java.time.LocalDate;

@Repository
public interface IRoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT (COUNT(b) > 0) FROM Booking b JOIN b.rooms r WHERE r.id = :roomId " +
            "AND b.checkIn <= :currentDate " +
            "AND b.checkOut >= :currentDate ")
    boolean notIsAvailable(@Param("currentDate") LocalDate currentDate, @Param("roomId") Integer roomId);

    @Query("SELECT (COUNT(b) > 0) FROM Booking b JOIN b.rooms r WHERE r.id = :roomId " +
            "AND b.checkIn < :checkOut " +
            "AND b.checkOut > :checkIn ")
    Boolean countOverlappingReservations(@Param("roomId") Integer roomId,
                                         @Param("checkIn") LocalDate checkIn,
                                         @Param("checkOut") LocalDate checkOut);
}