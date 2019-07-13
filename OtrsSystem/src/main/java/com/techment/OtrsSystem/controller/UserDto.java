package com.techment.OtrsSystem.controller;

import javax.validation.constraints.NotNull;

public class UserDto {
    private long id;

    @NotNull
    private String username;

    private String password;


    private String firstName;

    private String middleName;


    private String lastName;


    private String employeeId;



    private String phoneNumber;


    private String workingNumber;


    private String extensionLandline;


    private String landlineNumber;


    private String gender;

    private String designation;

    private String department;

    public UserDto(@NotNull String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWorkingNumber() {
        return workingNumber;
    }

    public String getExtensionLandline() {
        return extensionLandline;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDepartment() {
        return department;
    }
}
