package com.techment.OtrsSystem.repository;

import com.techment.OtrsSystem.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByStatusName(String statusName);
}
