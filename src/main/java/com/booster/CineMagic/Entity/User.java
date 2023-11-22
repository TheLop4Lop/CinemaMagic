package com.booster.CineMagic.Entity;

import com.booster.CineMagic.Enum.Account;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "UserCinema")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_User")
    private Integer id;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Name")
    private String name;

    @Email(message = "Not a valid Email")
    @NotNull(message = "Email is Null")
    @NotEmpty(message = "Email is Empty")
    @Column(name = "Email")
    private String eMail;

    @Positive(message = "Not a positive number")
    @Column(name = "Age")
    private int age;

    @NotNull(message = "Username is Null")
    @NotEmpty(message = "Username is Empty")
    @Column(name = "Username")
    private String username;

    @NotNull(message = "Password is Null")
    @NotEmpty(message = "Password is Empty")
    @Column(name = "Password")
    private String password;

    @NotNull(message = "Account is Null")
    @Column(name = "Account")
    @Enumerated(EnumType.STRING)
    private Account account;

    public User() {

    }

    public User(Integer id, String name, String eMail, int age, String username, String password, Account account) {
        this.id = id;
        this.name = name;
        this.eMail = eMail;
        this.age = age;
        this.username = username;
        this.password = password;
        this.account = account;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
