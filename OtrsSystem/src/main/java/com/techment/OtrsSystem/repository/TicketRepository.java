package com.techment.OtrsSystem.repository;

import com.techment.OtrsSystem.domain.Category;
import com.techment.OtrsSystem.domain.Status;
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

    Page<Ticket> findByAssignedUser(User user, Pageable pageable);

    Page<Ticket> findByCategoryAndStatus(Category category, Status status, Pageable pageable);

    //searching
    Page<Ticket> findByTicketTitleIgnoreCaseContaining(String title, Pageable pageable);

    Page<Ticket> findByTicketTitleAndUserIgnoreCaseContaining(String title, User user, Pageable pageable);

    Page<Ticket> findByStatus(Status status, Pageable pageable);

    Page<Ticket> findByStatusAndUser(Status status, User user, Pageable pageable);

    Page<Ticket> findByDueDate(Timestamp date, Pageable pageable);

    Page<Ticket> findByCategory(Category category, Pageable pageable);

    Page<Ticket> findByCreatedAt(Timestamp createdAt, Pageable pageable);

    //searching end

    long countTicketByCategory(Category category);

    long countTicketByCategoryAndStatus(Category category, Status status);
}
