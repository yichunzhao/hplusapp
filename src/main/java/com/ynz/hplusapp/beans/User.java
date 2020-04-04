package com.ynz.hplusapp.beans;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Size(min = 6, message = "{userName.not.empty}")
    private String userName;

    @Pattern(regexp = "", message = "{password.invalid}")
    private String password;
    private String gender;

    @NotNull(message = "{activity.must.have}")
    private String activity;

    @NotEmpty(message = "{firstName.cannot.empty}")
    private String firstName;
    private String lastName;

    private Date dateOfBirth;

    public User() {
    }

    public User(String userName, String password, String gender, String activity, String firstName, String lastName, Date dateOfBirth) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.activity = activity;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
