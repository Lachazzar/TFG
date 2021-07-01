package es.udc.tfgproject.backend.model.entities;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserBD, Long> {

    boolean existsByUserName(String userName);

    Optional<UserBD> findByUserName(String userName);

}
