package mateacademy.internetshop.model;

import java.util.List;

public class User {
    private final Long id;
    private List<Order> orders;
    private Bucket bucket;
    private String name;
    private String surname;
    private String password;
    private String login;

    public User() {
        this.id = 1L; //IdGenerator.getGeneratedId();
        this.bucket = new Bucket();
    }

    public User(String name) {
        this();
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
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

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public String toString() {
        return "User id: " + id + "; Orders : \n" + orders;
    }
}
