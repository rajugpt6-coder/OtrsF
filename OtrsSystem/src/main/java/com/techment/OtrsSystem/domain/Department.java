package com.techment.OtrsSystem.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbl_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "department_name")
    private String departmentName;

    //default constructor
    protected Department(){}

    //parametrized constructor

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
