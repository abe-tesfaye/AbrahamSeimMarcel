package com.example.nothinbutnet.Member;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;
    private String firstName;  // New field for first name
    private String lastName;   // New field for last name
    private String email;
    private String username;
    private String password;
    private Date joinDate;
    private Date updatedDate;
    private String status;  // For example, "active" or "suspended"

    // No-argument constructor required by JPA
    public Member() {}

    // Parameterized constructor
    public Member(String firstName, String lastName, String email, String username, String password, Date joinDate, Date updatedDate, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.joinDate = joinDate;
        this.updatedDate = updatedDate;
        this.status = status;
    }

    // Getters and Setters
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // New method to update the updatedDate
    public void updateLastModified() {
        this.updatedDate = new Date(); // Set updatedDate to the current date
    }
}