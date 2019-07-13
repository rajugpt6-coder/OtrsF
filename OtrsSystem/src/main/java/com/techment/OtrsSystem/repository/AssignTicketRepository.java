package com.techment.OtrsSystem.repository;

import com.techment.OtrsSystem.domain.AssignTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignTicketRepository extends JpaRepository<AssignTicket, Long> {
}
