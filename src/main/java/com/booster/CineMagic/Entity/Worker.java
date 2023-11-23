package com.booster.CineMagic.Entity;

import com.booster.CineMagic.Enum.WorkerType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Worker")
    private Integer id;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Name")
    private String name;

    @NotNull(message = "Date is Null")
    @Column(name = "Register_Date")
    private int registerDate;

    @Email(message = "Not a valid Email")
    @NotNull(message = "Email is Null")
    @NotEmpty(message = "Email is Empty")
    @Column(name = "Email")
    private String eMail;

    @NotNull(message = "Username is Null")
    @NotEmpty(message = "Username is Empty")
    @Column(name = "Username")
    private String username;

    @NotNull(message = "Password is Null")
    @NotEmpty(message = "Password is Empty")
    @Column(name = "Password")
    private String password;

    @NotNull(message = "Account is Null")
    @Column(name = "Worker_Type")
    @Enumerated(EnumType.STRING)
    private WorkerType type;

    public Worker() {

    }

    public Worker(Integer id, String name, int registerDate, String eMail, String username, String password, WorkerType type) {
        this.id = id;
        this.name = name;
        this.registerDate = registerDate;
        this.eMail = eMail;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(int registerDate) {
        this.registerDate = registerDate;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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

    public WorkerType getType() {
        return type;
    }

    public void setType(WorkerType type) {
        this.type = type;
    }
}
