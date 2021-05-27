package es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyDao extends JpaRepository<Allergy, Long> {

    Optional<Allergy> findByAllergyName(String allergyName);

}
