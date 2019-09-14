package mateacademy.internetshop.service;

import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;

import java.util.List;

public interface BucketService {

    Bucket addItem(Long bucketId, Long itemId);
    Bucket clear(Long bucketId);
    List<Item> getAllItems(Long bucketId);

    Bucket create(Bucket bucket);
    Bucket get(Long bucketId);
    Bucket update(Bucket bucket);
    void delete(Long bucketId);
}
