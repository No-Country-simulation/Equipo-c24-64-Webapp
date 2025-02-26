package gestionDeReservas.repository;

import gestionDeReservas.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IBookingRepository extends JpaRepository<Booking,Integer> {
    @Query("SELECT (COUNT(b) > 0) FROM Booking b JOIN b.rooms r WHERE r.id = :roomId " +
            "AND ((:checkIn BETWEEN b.checkIn AND b.checkOut) " +
            "OR (:checkOut BETWEEN b.checkIn AND b.checkOut) " +
            "OR (b.checkIn BETWEEN :checkIn AND :checkOut))")
    Boolean countOverlappingReservations(@Param("roomId") Integer roomId,
                                         @Param("checkIn") LocalDate checkIn,
                                         @Param("checkOut") LocalDate checkOut);



}