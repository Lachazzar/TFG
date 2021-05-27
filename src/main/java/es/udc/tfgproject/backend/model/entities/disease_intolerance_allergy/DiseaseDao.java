package es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseDao extends JpaRepository<Disease, Long> {

    Optional<Disease> findByDiseaseName(String diseaseName);
}
