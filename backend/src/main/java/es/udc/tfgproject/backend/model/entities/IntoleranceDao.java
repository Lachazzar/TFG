package es.udc.tfgproject.backend.model.entities;

import java.util.Optional;

public interface IntoleranceDao {

    Optional<Intolerance> findByIntoleranceName(String intoleranceName);

}
