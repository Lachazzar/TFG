package es.udc.tfgproject.backend.model.entities;

import java.util.Optional;

public interface AllergyDao {

    Optional<Allergy> findByAllergyName(String allergyName);

}
