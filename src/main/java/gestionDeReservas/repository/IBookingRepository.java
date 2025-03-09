package gestionDeReservas.repository;

import gestionDeReservas.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking,Integer> {
    @Query("SELECT (COUNT(b) > 0) FROM Booking b JOIN b.rooms r WHERE r.id = :roomId " +
            "AND b.checkIn < :checkOut " +
            "AND b.checkOut > :checkIn ")
    Boolean countOverlappingReservations(@Param("roomId") Integer roomId,
                                         @Param("checkIn") LocalDate checkIn,
                                         @Param("checkOut") LocalDate checkOut);

    @Query("SELECT b FROM Booking b WHERE b.user.email = :email OR b.visitor.email = :email")
    List<Booking> findBookingsFromUser(@Param("email") String email);
}