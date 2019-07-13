package com.techment.OtrsSystem.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbl_assign_ticket")
public class AssignTicket {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "fk_assigned_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "fk_assigned_ticket")
    private Ticket ticket;

    public AssignTicket(User user, Ticket ticket) {
        this.user = user;
        this.ticket = ticket;
    }

    protected AssignTicket() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
