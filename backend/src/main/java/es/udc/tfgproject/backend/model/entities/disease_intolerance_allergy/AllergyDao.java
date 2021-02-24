package es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy;

import java.util.Optional;

public interface AllergyDao {

    Optional<Allergy> findByName(String name);

}
