
package com.techment.OtrsSystem.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "ticket_title")
    @NotNull
    private String ticketTitle;

    @Column(name = "ticket_description")
    @NotNull
    private String ticketDescription;

    @Column(name = "created_at")
    @NotNull
    private Timestamp createdAt;

    @Column(name = "created_by")
    @NotNull
    private String createdBy;

    @Column(name = "due_date")
    @NotNull
    private Timestamp dueDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "ticket_activation_flag")
    @NotNull
    private boolean ticketActivationFlag;

    @ManyToOne
    @JoinColumn(name = "fk_status")
    @NotNull
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fk_category")
    @NotNull
    private Category category;

    @ManyToOne
    private User user;


    protected Ticket(){}


    public Ticket(@NotNull String ticketTitle, @NotNull String ticketDescription, @NotNull Timestamp createdAt,
                  @NotNull String createdBy, @NotNull Timestamp dueDate, @NotNull boolean ticketActivationFlag,
                  @NotNull Status status, @NotNull Category category, User user) {

        this.ticketTitle = ticketTitle;
        this.ticketDescription = ticketDescription;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.dueDate = dueDate;
        this.ticketActivationFlag = ticketActivationFlag;
        this.status = status;
        this.category = category;
        this.user = user;
    }

    public Ticket(@NotNull String ticketTitle, @NotNull String ticketDescription, @NotNull Timestamp createdAt,
                  @NotNull String createdBy, @NotNull Timestamp dueDate, String updatedBy, Timestamp updatedAt,
                  @NotNull boolean ticketActivationFlag, @NotNull Status status, @NotNull Category category, User user) {

        this.ticketTitle = ticketTitle;
        this.ticketDescription = ticketDescription;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.dueDate = dueDate;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.ticketActivationFlag = ticketActivationFlag;
        this.status = status;
        this.category = category;
        this.user = user;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getTicketActivationFlag() {
        return ticketActivationFlag;
    }

    public void setTicketActivationFlag(boolean ticketActivationFlag) {
        this.ticketActivationFlag = ticketActivationFlag;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
