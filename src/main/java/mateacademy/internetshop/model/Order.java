package mateacademy.internetshop.model;

import java.util.List;

import mateacademy.internetshop.IdGenerator;

public class Order {
    private final Long orderId;
    private Long userId;
    private List<Item> items;

    public Order(List<Item> items, Long userId) {
        this.orderId = IdGenerator.getOrderGeneratedId();
        this.userId = userId;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
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
}
