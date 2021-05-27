package es.udc.tfgproject.backend.model.entities.medicamentInformation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyDao extends JpaRepository<Family, Long> {

    Optional<Family> findByFamilyName(String familyName);

}
