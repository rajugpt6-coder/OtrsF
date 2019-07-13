package com.techment.OtrsSystem.controller;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserDto {
    private long id;

    @NotNull
    private String username;


    @NotNull
    private String firstName;

    @NotNull
    private String middleName;


    private String lastName;


    @NotNull
    private String employeeId;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String workingNumber;


    private String extensionLandline;


    private String landlineNumber;

    private List<String> featureAccessList;


    @NotNull
    private String gender;

    @NotNull
    private String designation;

    private String department;

    private boolean flag;

    public UserDto(long id, @NotNull String username, @NotNull String firstName,
                   @NotNull String middleName, String lastName, @NotNull String employeeId,
                   @NotNull String phoneNumber, @NotNull String workingNumber, String extensionLandline,
                   String landlineNumber, List<String> featureAccessList, @NotNull String gender,
                   @NotNull String designation, String department, boolean flag) {

        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.workingNumber = workingNumber;
        this.extensionLandline = extensionLandline;
        this.landlineNumber = landlineNumber;
        this.featureAccessList = featureAccessList;
        this.gender = gender;
        this.designation = designation;
        this.department = department;
        this.flag = flag;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public List<String> getFeatureAccessList() {
        return featureAccessList;
    }

    public boolean isFlag() {
        return flag;
    }
}
