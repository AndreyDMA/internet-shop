package mateacademy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long bucketId;
    private List<Item> items;
    private Long userId;

    public Bucket(Long bucketId, Long userId, List<Item> items) {
        this.bucketId = bucketId;
        this.items = items;
        this.userId = userId;
    }

    public Bucket(Long bucketId, Long userId) {
        this.bucketId = bucketId;
        items = new ArrayList<>();
        this.userId = userId;
    }

    public Bucket(Long userId) {
        items = new ArrayList<>();
        this.userId = userId;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
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
