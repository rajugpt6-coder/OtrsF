package com.techment.OtrsSystem.repository;

import com.techment.OtrsSystem.domain.Department;
import com.techment.OtrsSystem.domain.Designation;
import com.techment.OtrsSystem.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Page<User> findByEmployeeIdIgnoreCaseContaining(String employeeId, Pageable pageable);

    Page<User> findByFirstNameIgnoreCaseContaining(String firstName, Pageable pageable);

    Page<User> findByFlag(boolean flag, Pageable pageable);

    Page<User> findByPhoneNumberIgnoreCaseContaining(String phoneNumber, Pageable pageable);

    Page<User> findByDepartment(Department department, Pageable pageable);

    Page<User> findByDesignation(Designation designation, Pageable pageable);
}
