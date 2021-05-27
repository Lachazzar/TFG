package es.udc.tfgproject.backend.model.entities.medicamentInformation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentDao extends JpaRepository<Medicament, Long> {

    Optional<Medicament> findByName(String medicamentName);
}
