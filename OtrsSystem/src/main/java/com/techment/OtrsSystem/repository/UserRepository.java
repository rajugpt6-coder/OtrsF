package com.techment.OtrsSystem.repository;

import com.techment.OtrsSystem.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Page<User> findByEmployeeId(String employeeId, Pageable pageable);

    Page<User> findByFirstName(String firstName, Pageable pageable);

    Page<User> findByFlag(boolean flag, Pageable pageable);
}
