package es.udc.tfgproject.backend.model.entities;

import java.util.Optional;

public interface DiseaseDao {

    Optional<Disease> findByDiseaseName(String diseaseName);

}
