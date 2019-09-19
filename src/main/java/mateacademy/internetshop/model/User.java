package mateacademy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

import mateacademy.internetshop.IdGenerator;

public class User {
    private final Long userId;
    private Bucket bucket;
    private List<Order> orders;
    private String name;
    private String surname;
    private String password;
    private String login;

    public User() {
        this.userId = IdGenerator.getUserGeneratedId();
        orders = new ArrayList<>();
        bucket = new Bucket(userId);
    }

    public User(String name) {
        this();
        this.name = name;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

}
