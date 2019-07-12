package com.techment.OtrsSystem.repository;

import com.techment.OtrsSystem.domain.Features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeaturesRepository extends JpaRepository<Features, Long> {
    Optional<Features> findByFeatureName(String featureName);
}
