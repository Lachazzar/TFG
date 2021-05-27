package es.udc.tfgproject.backend.model.entities.medicamentInformation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemicalComponentDao extends JpaRepository<ChemicalComponent, Long> {

    Optional<ChemicalComponent> findByComponentName(String componentName);

}
