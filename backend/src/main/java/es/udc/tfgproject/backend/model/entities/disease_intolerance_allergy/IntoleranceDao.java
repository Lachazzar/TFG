package es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy;

import java.util.Optional;

public interface IntoleranceDao {

    Optional<Intolerance> findByName(String name);

}
