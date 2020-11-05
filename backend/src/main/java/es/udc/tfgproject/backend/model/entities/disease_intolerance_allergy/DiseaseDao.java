package es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy;

import java.util.Optional;

public interface DiseaseDao {

    Optional<Disease> findByDiseaseName(String diseaseName);

}
