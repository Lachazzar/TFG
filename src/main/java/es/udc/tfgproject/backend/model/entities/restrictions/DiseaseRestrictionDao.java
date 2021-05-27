package es.udc.tfgproject.backend.model.entities.restrictions;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;

public interface DiseaseRestrictionDao extends JpaRepository<DiseaseRestriction, Long> {
    Optional<DiseaseRestriction> findByDisease(Disease disease);
}
