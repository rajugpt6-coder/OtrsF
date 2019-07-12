package com.techment.OtrsSystem.repository;

import com.techment.OtrsSystem.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
