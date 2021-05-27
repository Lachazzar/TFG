package es.udc.tfgproject.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<UserBD, Long> {
	
	boolean existsByUserName(String userName);

	Optional<UserBD> findByUserName(String userName);
	
}
