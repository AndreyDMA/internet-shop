package mateacademy.internetshop.model;

import mateacademy.internetshop.IdGenerator;

public class Order {
    private final Long id;
    private Long bucketId;

    public Order(Long bucketId) {
        this.id = IdGenerator.getGeneratedId();
        this.bucketId = bucketId;
    }

    public Long getId() {
        return id;
    }

    public Long getBucketId() {
        return bucketId;
    }
}
