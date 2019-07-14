package com.techment.OtrsSystem.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    @JsonIgnore
    private String password;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "employee_id")
    @NotNull
    private String employeeId;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @Column(name = "working_number")
    @NotNull
    private String workingNumber;

    @Column(name = "extension_landline")
    private String extensionLandline;

    @Column(name = "landline_number")
    private String landlineNumber;

    @Column(name = "flag")
    @NotNull
    private boolean flag;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_gender")
    private Gender gender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_designation")
    private Designation designation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_department")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_features", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id",
                    referencedColumnName = "id"))
    private List<Features> features;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_ticket")
    private List<Ticket> tickets;


    //default constructor
    protected User() {}

    //parametrized constructor


    public User(@NotNull String email, @NotNull String password, @NotNull String firstName, String middleName, @NotNull String lastName,
                @NotNull String employeeId, @NotNull String phoneNumber, @NotNull String workingNumber, String extensionLandline, String landlineNumber,
                @NotNull boolean flag, Timestamp createdAt, String createdBy, Timestamp updatedAt,
                String updatedBy, Gender gender, Designation designation, Department department, List<Features> features, List<Ticket> tickets) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.workingNumber = workingNumber;
        this.extensionLandline = extensionLandline;
        this.landlineNumber = landlineNumber;
        this.flag = flag;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.gender = gender;
        this.designation = designation;
        this.department = department;
        this.features = features;
        this.tickets = tickets;
    }

    //overloaded constructor

    public User(@NotNull String email, @NotNull String password, @NotNull String firstName, String middleName, @NotNull String lastName,
                @NotNull String employeeId, @NotNull String phoneNumber, @NotNull String workingNumber, String extensionLandline, String landlineNumber,
                Gender gender, Designation designation, Department department, @NotNull boolean flag, Timestamp createdAt, String createdBy,
                List<Features> features) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.workingNumber = workingNumber;
        this.extensionLandline = extensionLandline;
        this.landlineNumber = landlineNumber;
        this.gender = gender;
        this.designation = designation;
        this.department = department;
        this.features = features;
        this.flag = flag;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkingNumber() {
        return workingNumber;
    }

    public void setWorkingNumber(String workingNumber) {
        this.workingNumber = workingNumber;
    }

    public String getExtensionLandline() {
        return extensionLandline;
    }

    public void setExtensionLandline(String extensionLandline) {
        this.extensionLandline = extensionLandline;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}


