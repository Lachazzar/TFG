package es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IntoleranceDao extends JpaRepository<Intolerance, Long> {

    Optional<Intolerance> findByIntoleranceName(String intoleranceName);
}
