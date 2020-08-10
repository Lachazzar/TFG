package es.udc.tfgproject.backend.model.entities;

import java.util.Optional;

public interface CommercialMedicamentDao {

    Optional<CommercialMedicament> findByName(String name);

}
