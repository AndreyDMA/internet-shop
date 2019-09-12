package mateacademy.internetshop.model;

import java.util.List;

import mateacademy.internetshop.IdGenerator;

public class Bucket {
    private final Long id;
    private List<Item> items;
    private final Long userId;

    public Bucket(Long userId) {
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }
}
