package mateacademy.internetshop.model;

import java.util.List;

public class Order {
    private Long orderId;
    private Long userId;
    private List<Item> items;

    public Order(List<Item> items, Long userId) {
        this.userId = userId;
        this.items = items;
    }

    public Order(Long orderId, Long userId, List<Item> items) {
        this.orderId = orderId;
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
