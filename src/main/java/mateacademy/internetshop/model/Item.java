package mateacademy.internetshop.model;

public class Item {
    private Long itemId;
    private String name;
    private Double price;

    public Item() {
    }

    public Item(Long itemId) {
        this.itemId = itemId;
    }

    public Item(Long itemId, String name, Double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
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
