package mateacademy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

import mateacademy.internetshop.IdGenerator;

public class Bucket {
    private final Long bucketId;
    private List<Item> items;
    private Long userId;

    public Bucket(Long userId) {
        this.bucketId = IdGenerator.getBucketGeneratedId();
        items = new ArrayList<>();
        this.userId = userId;
    }

    public Long getBucketId() {
        return bucketId;
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
