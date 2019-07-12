package com.techment.OtrsSystem.repository;

import com.techment.OtrsSystem.domain.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {
    Optional<Designation> findByDesignationName(String designationName);
}
