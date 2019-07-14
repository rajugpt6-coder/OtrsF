package com.techment.OtrsSystem.service;

import com.techment.OtrsSystem.domain.Ticket;
import com.techment.OtrsSystem.repository.*;
import com.techment.OtrsSystem.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TicketService {

    public static final long MAX_DAYS = 8;
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CategoryRepository categoryRepository;





    public Ticket createTicket(String category, String description, String status, String title, long id, String token){
        LOGGER.info("New user attempting raise ticket");
        Ticket ticket = null;
        if(userRepository.existsById(id) &&
                userRepository.findById(id).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(userService.filterToken(token))) &&
                userRepository.findById(id).get().isFlag()) {
            ticket = ticketRepository.save(new Ticket(title, description, Timestamp.valueOf(LocalDateTime.now()),jwtProvider.getUsername(userService.filterToken(token)), Timestamp.valueOf(LocalDateTime.now().plusDays(MAX_DAYS)),true,
                    statusRepository.findByStatusName(status).get(), categoryRepository.findByCategoryName(category).get(),
                    userRepository.findById(id).get()));
        }
        return ticket;

    }

    public Page<Ticket> findTicketsByUserId (Long id, Pageable pageable, String token) {
        LOGGER.info("New user attempting to retieve tickets ");
        Page<Ticket> ticket = null;
        token = userService.filterToken(token);

        if(userRepository.existsById(id) && userRepository.findById(id).get().isFlag()
                && userRepository.findById(id).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(token)) ) {
            return ticketRepository.findByUser(userRepository.findById(id).get(), pageable);
        }
        return ticket;

    }

    public Page<Ticket> getAllTickets(Pageable pageable) throws NoSuchElementException {
        LOGGER.info("Attempting to retrieve all tickets ");
        return ticketRepository.findAll(pageable);
    }

    public Ticket findTicketById(long ticketId, long userId, String token) throws NoSuchElementException{
        LOGGER.info("retrieving..");
        token = userService.filterToken(token);
        if(userRepository.findById(userId).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(token)) &&
                ticketRepository.findById(ticketId).get().getUser().getEmail().equalsIgnoreCase(userRepository.findByEmail(jwtProvider.getUsername(token)).get().getEmail())) {
            LOGGER.info("trying to retrieve ticket details");
            return ticketRepository.findById(ticketId).orElseThrow(() -> new NoSuchElementException("No tickets found"));
        }
        return null;
    }

    public String findTicketStatus(long userId, long ticketId, String token ){
        LOGGER.info("Trying to retrieve ticket status");
        token = userService.filterToken(token);
        if(userRepository.findById(userId).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(token)) &&
                ticketRepository.findById(ticketId).get().getUser().getEmail().equalsIgnoreCase(userRepository.findByEmail(jwtProvider.getUsername(token)).get().getEmail())) {
            return ticketRepository.findById(ticketId).get().getStatus().getStatusName();
        }
        return null;
    }

    public Optional<String> updateStatus(String status, long ticketId, String token, long id) {
        token = userService.filterToken(token);
        Optional<String> rtn = Optional.empty();
        if (userRepository.findById(id).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(token))) {
            Optional<Ticket> ticket = ticketRepository.findById(ticketId);
            ticket.get().setStatus(statusRepository.findByStatusName(status).get());
            ticketRepository.save(ticket.get());
            rtn = Optional.of("{\"status\":\"success\",\"msg\":\"status has been updated successfully\"}");
        } else {
            rtn = Optional.of("{\"status\":\"failure\",\"msg\":\"Authorization failed\"}");
        }
        return rtn;
    }

    public void assignTicket(long userId, long ticketId, String token){
        token = userService.filterToken(token);

        if(userRepository.existsById(userId) && ticketRepository.existsById(ticketId) &&
            userRepository.findById(userId).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(token))){
            Ticket ticket = ticketRepository.findById(ticketId).get();
            ticket.setAssignedUser(userRepository.findById(userId).get());
            ticketRepository.save(ticket);
        }

    }

    public Page<Ticket> getAssignedTickets(long userId, String token, Pageable pageable) {
        token = userService.filterToken(token);
        if(userRepository.existsById(userId) &&
                userRepository.findById(userId).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(token))){
            return ticketRepository.findByAssignedUser(userRepository.findById(userId).get(), pageable);
        }
        return null;
    }

    public Page<Ticket> getTicketsForResolve(long userId, String token, Pageable pageable){
        token = userService.filterToken(token);
        if(userRepository.existsById(userId) &&
                userRepository.findById(userId).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(token))){

           return ticketRepository.findByCategoryAndStatus(categoryRepository.findByCategoryName(userRepository.findById(userId).get().getDepartment().getDepartmentName()).get(),
                    statusRepository.findByStatusName("PENDING").get(), pageable);
        }
        return null;
    }

    public void deactivateTicketByUser(long userId, long ticketId, String token) {
        token = userService.filterToken(token);
        if(userRepository.existsById(userId) && ticketRepository.existsById(ticketId)
            && userRepository.findById(userId).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(token))
            && ticketRepository.findById(ticketId).get().getUser().equals(userRepository.findById(userId).get())) {

            Ticket ticket = ticketRepository.findById(ticketId).get();
            ticket.setTicketActivationFlag(false);
            ticketRepository.save(ticket);
        }
    }

    public void deactivateTicketByAdmin( long ticketId) {

        if(ticketRepository.existsById(ticketId)) {

            Ticket ticket = ticketRepository.findById(ticketId).get();
            ticket.setTicketActivationFlag(false);
            ticketRepository.save(ticket);
        }
    }

    //searching
    public Page<Ticket> findTicketsByTitle(String title, Pageable pageable){
        return ticketRepository.findByTicketTitle(title, pageable);
    }

    public Page<Ticket> findTicketsByTitleAndUser(String title, long userId, Pageable pageable, String token){
        token = userService.filterToken(token);
        if(userRepository.findById(userId).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(token))) {
            return ticketRepository.findByTicketTitleAndUser(title, userRepository.findById(userId).get(), pageable);
        }
        return null;
    }

    public Page<Ticket> findTicketsByTitleAndStatus(String status, long userId, Pageable pageable, String token){
        token = userService.filterToken(token);
        if(userRepository.findById(userId).get().getEmail().equalsIgnoreCase(jwtProvider.getUsername(token))) {
            return ticketRepository.findByStatusAndUser(statusRepository.findByStatusName(status).get(), userRepository.findById(userId).get(), pageable);
        }
        return null;
    }

    public Page<Ticket> findTicketsByStatus(String status, Pageable pageable){
        return ticketRepository.findByStatus(statusRepository.findByStatusName(status).get(), pageable);
    }

    public Page<Ticket> findTicketsByCategory(String category, Pageable pageable){
        return ticketRepository.findByCategory(categoryRepository.findByCategoryName(category).get(), pageable);
    }

    public Page<Ticket> findTicketsByDueDate(Timestamp dueDate, Pageable pageable){
        return ticketRepository.findByDueDate(dueDate, pageable);
    }

    public Page<Ticket> findTicketsByCreatedAt(Timestamp createdAt, Pageable pageable){
        return ticketRepository.findByCreatedAt(createdAt, pageable);
    }

    //Analytics part

    public long countAllTickets () {
        return userRepository.count();
    }

    public long countTicketByCategory (String category) {
        return ticketRepository.countTicketByCategory(categoryRepository.findByCategoryName(category).get());
    }

    public long countTicketByCategoryAndStatus (String category, String status){
        return ticketRepository.countTicketByCategoryAndStatus(categoryRepository.findByCategoryName(category).get(), statusRepository.findByStatusName(status).get());
    }
}
