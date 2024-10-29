package com.beesby.api.repositories;

import com.beesby.api.entities.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID>, JpaSpecificationExecutor<User> {

    Optional<User> findById(UUID id);

    User save(User user);

    Optional<User> findByCpf(String cpf);

    Boolean existsByCpf(String cpf);
}
