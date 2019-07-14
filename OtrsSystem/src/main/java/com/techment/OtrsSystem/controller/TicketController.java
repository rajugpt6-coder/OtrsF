package com.techment.OtrsSystem.controller;

import com.techment.OtrsSystem.domain.Ticket;
import com.techment.OtrsSystem.service.TicketService;
import com.techment.OtrsSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/users/{id}")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @PostMapping("/ticket")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR') or hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Ticket> raiseTicket(@RequestBody @Validated TicketDto ticketDto, @PathVariable("id") long id,
                                        @RequestHeader(value = "Authorization") String token){

        return Optional.ofNullable(ticketService.createTicket(ticketDto.getCategory(), ticketDto.getDescription(),
                ticketDto.getStatus(),
                ticketDto.getTitle(), id, token));

    }

    @GetMapping("/tickets")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR') or hasRole('ROLE_USER')")
    public Page<Ticket> getTickets(@PathVariable("id") long id, Pageable pageable, @RequestHeader(value = "Authorization") String token){
        return ticketService.findTicketsByUserId(id, pageable, token);
//            PagedResources<Ticket> result =  pagedResourcesAssembler.toResource(tickets, assembler);
//            return ticketService.findTicketsByUserId(id, pageable) ;
//        }
//        return null;
    }

    @GetMapping("/tickets/all")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<Ticket> getAllTickets(Pageable pageable) {
        return ticketService.getAllTickets(pageable);
    }

    @GetMapping("/tickets/{ticketId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR') or hasRole('ROLE_USER')")
    public Ticket getTicketDetails (@PathVariable("ticketId") Long ticketId, @PathVariable("id") long userId,
                                    @RequestHeader(value = "Authorization") String token) {

        return ticketService.findTicketById(ticketId, userId, token);

    }

    @GetMapping("/tickets/{ticketId}/status")
    public String getTicketStatus(@PathVariable("userId") long userId, @PathVariable("ticketId") long ticketId,
                                 @RequestHeader(value = "Authorization") String token) {
        return ticketService.findTicketStatus(userId, ticketId, token);
    }

    @PatchMapping("/tickets/{ticketId}/status/{status}")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<String> updateStatus(@PathVariable("ticketId") long ticketId, @PathVariable("status") String status, @PathVariable("id") long id,
                                         @RequestHeader(value = "Authorization") String token) {
        return ticketService.updateStatus(status, ticketId, token, id);

    }

    @GetMapping("/tickets/resolveTickets")
    public Page<Ticket> getTicketsToResolve(@PathVariable("id") long userId, @RequestHeader("Authorization") String token,
                                            Pageable pageable) {
        return ticketService.getTicketsForResolve(userId, token, pageable);
    }

    @PostMapping("/assignTicket/ticket/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public void assignTicket (@PathVariable("id") long userId, @PathVariable("ticketId") long ticketId,
                               @RequestHeader("Authorization") String token) {
        ticketService.assignTicket(userId, ticketId, token);

    }

    @GetMapping("/assignTickets")
    public Page<Ticket> getAssignedTickets(@PathVariable("id") long userId,
                                           @RequestHeader("Authorization") String token, Pageable pageable){
        return ticketService.getAssignedTickets(userId,token, pageable);
    }

    @PatchMapping("/tickets/{ticketId}/deactivate")
    public void deactivateTicketByUser(@PathVariable("id") long userId, @PathVariable("ticketId") long ticketId,
                                       @RequestHeader("Authorization") String token) {
        ticketService.deactivateTicketByUser(userId, ticketId, token);
    }

    @PatchMapping("/tickets/{ticketId}/admin/deactivate")
    public void deactivateTicketByAdmin(@PathVariable("ticketId") long ticketId){
        ticketService.deactivateTicketByAdmin(ticketId);
    }

        //searching code

    @GetMapping("/tickets/search/title/{title}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR')")
    public Page<Ticket> getTicketsByTitle(@PathVariable("title") String title, Pageable pageable) {
        return ticketService.findTicketsByTitle(title, pageable);
    }

    @GetMapping("/tickets/search/status/{status}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR')")
    public Page<Ticket> getTicketsByStatus(@PathVariable("status") String status, Pageable pageable) {
        return ticketService.findTicketsByStatus(status, pageable);
    }

    @GetMapping("/tickets/search/user/title/{title}")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR')")
    public Page<Ticket> getTicketsByTitleAndUser(@PathVariable("id") long id, @PathVariable("title") String title,
                                                 @RequestHeader(value = "Authorization") String token, Pageable pageable){
        return ticketService.findTicketsByTitleAndUser(title, id, pageable, token);
    }

    @GetMapping("/tickets/search/user/status/{status}")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR')")
    public Page<Ticket> getTicketsByStatusAndUser(@PathVariable("id") long id, @PathVariable("status") String status,
                                                  @RequestHeader(value = "Authorization") String token, Pageable pageable){
        return ticketService.findTicketsByTitleAndUser(status, id, pageable, token);
    }

    @GetMapping("/tickets/search/dueDate/{dueDate}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR') or hasRole('ROLE_USER')")
    public Page<Ticket> getTicketsByDueDate(@PathVariable("dueDate") String dueDate, Pageable pageable) {
        return ticketService.findTicketsByDueDate(Timestamp.valueOf(dueDate), pageable);
    }

    @GetMapping("/tickets/search/category/{category}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<Ticket> getTicketsByCategory(@PathVariable("category") String category, Pageable pageable) {
        return ticketService.findTicketsByCategory(category, pageable);
    }

    @GetMapping("/tickets/search/dueDate/{createdAt}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR') or hasRole('ROLE_USER')")
    public Page<Ticket> getTicketsByCreatedDate(@PathVariable("createdAt") String createdAt, Pageable pageable) {
        return ticketService.findTicketsByCreatedAt(Timestamp.valueOf(createdAt), pageable);
    }


    // Analytics code below

    @GetMapping("/ticktes/count")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public long getIsuueCount() {
        return ticketService.countAllTickets();
    }

    @GetMapping("/tickets/{category}/count")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public long getIssueByCategory(@PathVariable("category") String category ) {
        return ticketService.countTicketByCategory(category);
    }

    @GetMapping("/tickets/{category}/{status}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public long getIssueBycategoryAndStatus (@PathVariable("category") String category, @PathVariable("status") String status ) {
        return ticketService.countTicketByCategoryAndStatus(category, status);
    }


}
