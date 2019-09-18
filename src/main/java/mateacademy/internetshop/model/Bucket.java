package mateacademy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private final Long bucketId;
    private List<Item> items;
    private User user;

    public Bucket() {
        this.bucketId = 0L; //IdGenerator.getGeneratedId();
        items = new ArrayList<>();
    }

    public Bucket(User user) {
        this();
        this.user = user;
    }

    public Long getId() {
        return bucketId;
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
        return "Bucket id: " + bucketId + "; User: " + user + ";\n";
    }
}
