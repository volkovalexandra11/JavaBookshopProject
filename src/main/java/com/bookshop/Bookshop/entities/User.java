package com.bookshop.Bookshop.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    private String first_name;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    private String last_name;
    private String login;
    private String password;

    public User(String first_name, String second_name, String login, String password) {
        this.first_name = first_name;
        this.last_name = second_name;
        this.login = login;
        this.password = password;
    }

    public User() {

    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
