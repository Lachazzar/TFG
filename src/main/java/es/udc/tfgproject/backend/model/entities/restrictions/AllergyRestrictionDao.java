package es.udc.tfgproject.backend.model.entities.restrictions;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;

public interface AllergyRestrictionDao extends JpaRepository<AllergyRestriction, Long> {
    Optional<AllergyRestriction> findByAllergy(Allergy allergy);
}
