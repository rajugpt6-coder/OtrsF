package com.techment.OtrsSystem.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbl_status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "status_name")
    private String statusName;

    //default constructor
    protected Status(){}

    //Parametrized constructor

    public Status(String statusName) {
        this.statusName = statusName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
