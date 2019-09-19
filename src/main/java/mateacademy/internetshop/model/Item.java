package mateacademy.internetshop.model;

import mateacademy.internetshop.IdGenerator;

public class Item {
    private final Long itemId;
    private String name;
    private Double price;

    public Item() {
        this.itemId = IdGenerator.getItemGeneratedId();
    }

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
