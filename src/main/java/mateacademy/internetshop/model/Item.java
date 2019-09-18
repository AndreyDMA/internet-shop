package mateacademy.internetshop.model;

import java.util.List;

import mateacademy.internetshop.IdGenerator;

public class Item {
    private final Long id;
    private String name;
    private Double price;
    private List<Item> items;

    public Item() {
        this.id = IdGenerator.getGeneratedId();
    }

    public Item(String name, Double price) {
        this();
        this.name = name;
        this.price = price;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item id: " + id + "; name: " + name + "; price: " + price + ";\n";
    }
}
