package com.techment.OtrsSystem.repository;

import com.techment.OtrsSystem.domain.Ticket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {
}
