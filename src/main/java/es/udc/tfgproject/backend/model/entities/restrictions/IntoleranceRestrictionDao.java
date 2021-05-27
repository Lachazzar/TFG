package es.udc.tfgproject.backend.model.entities.restrictions;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;

public interface IntoleranceRestrictionDao extends JpaRepository<IntoleranceRestriction, Long> {
    Optional<IntoleranceRestriction> findByIntolerance(Intolerance intolerance);
}
