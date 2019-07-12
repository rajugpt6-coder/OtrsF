package com.techment.OtrsSystem.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "status_name")
    private String statusName;



    //default constructor
    protected Status() {}

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
