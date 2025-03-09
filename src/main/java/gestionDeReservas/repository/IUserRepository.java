package gestionDeReservas.repository;

import gestionDeReservas.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.username = :identifier" +
            " OR u.email = :identifier")
    Optional<User> findByUsernameOrEmail(@Param("identifier") String identifier);
    Boolean existsByEmailOrUsername(String email, String username);
    Optional<User> findByEmail(String email);
}