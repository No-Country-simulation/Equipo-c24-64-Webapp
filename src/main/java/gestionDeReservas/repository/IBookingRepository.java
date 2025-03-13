package gestionDeReservas.repository;

import gestionDeReservas.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking,Integer> {
    @Query("SELECT b FROM Booking b " +
            "LEFT JOIN b.user u " +
            "LEFT JOIN b.visitor v " +
            "WHERE u.email = :email OR v.email = :email")
    List<Booking> findBookingsFromUser(@Param("email") String email);
}