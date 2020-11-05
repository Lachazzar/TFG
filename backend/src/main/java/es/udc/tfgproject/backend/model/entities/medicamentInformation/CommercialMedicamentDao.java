package es.udc.tfgproject.backend.model.entities.medicamentInformation;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommercialMedicamentDao extends PagingAndSortingRepository<CommercialMedicament, Long> {

    Optional<CommercialMedicament> findByName(String name);

}
