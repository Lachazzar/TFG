package es.udc.tfgproject.backend.model.entities.medicamentInformation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommercialMedicamentDao extends JpaRepository<CommercialMedicament, Long> {

    Optional<CommercialMedicament> findByName(String commercialMedicamentName);

}
