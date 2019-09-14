package mateacademy.internetshop.model;

import mateacademy.internetshop.IdGenerator;

import java.util.List;

public class Order {
    private final Long id;
    private Long userId;
    private List<Item> items;

    public Order(List<Item> items, Long userId) {
        this.id = IdGenerator.getGeneratedId();
        this.userId = userId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order id: " + id + "; User id: " +  userId + ";\n" + items;
    }
}
