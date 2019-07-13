package com.techment.OtrsSystem.repository;

import com.techment.OtrsSystem.domain.Ticket;
import com.techment.OtrsSystem.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.sql.Timestamp;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {
    Page<Ticket> findByUser(User user, Pageable pageable);

    //searching
    Page<Ticket> findByTicketTitle(String title, Pageable pageable);

    Page<Ticket> findByTicketTitleAndUser(String title, User user, Pageable pageable);

    Page<Ticket> findByStatus(String status, Pageable pageable);

    Page<Ticket> findByStatusAndUser(String status, User user, Pageable pageable);

    Page<Ticket> findByDueDate(Timestamp date, Pageable pageable);

    Page<Ticket> findByCategory(String category, Pageable pageable);

    //searching end

    long countTicketByCategory(String category);

    long countTicketByCategoryAndStatus(String category, String status);
}
