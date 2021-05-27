package es.udc.tfgproject.backend.model.entities.restrictions;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularRestrictionDao extends JpaRepository<RegularRestriction, Long> {

    Optional<RegularRestriction> findByCode(String code);
}
