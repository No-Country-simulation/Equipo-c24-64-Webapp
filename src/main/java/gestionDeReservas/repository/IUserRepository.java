package gestionDeReservas.repository;

import gestionDeReservas.Model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Integer> {
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
