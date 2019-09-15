package mateacademy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

import mateacademy.internetshop.IdGenerator;

public class Bucket {
    private final Long id;
    private List<Item> items = new ArrayList<>();
    private User user;

    public Bucket() {
        this.id = IdGenerator.getGeneratedId();
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bucket id: " + id + "; User: " + user + ";\n";
    }
}
