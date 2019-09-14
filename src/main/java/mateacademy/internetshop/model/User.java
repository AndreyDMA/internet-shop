package mateacademy.internetshop.model;

import mateacademy.internetshop.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final Long id;
    private List<Order> orders;
    private Bucket bucket;

    public User() {
        this.id = IdGenerator.getGeneratedId();
        this.orders = new ArrayList<>();
        this.bucket = new Bucket();
    }

    public Long getId() {
        return id;
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
