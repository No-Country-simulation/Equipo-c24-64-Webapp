package gestionDeReservas.repository;

import gestionDeReservas.model.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVisitorRepository extends JpaRepository<Visitor,Integer> {
    boolean existsByEmail(String email);
    Optional<Visitor> findByEmail(String email);
}