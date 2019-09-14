package mateacademy.internetshop.service;

import java.util.List;

import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;

public interface BucketService {

    Bucket addItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);

    List<Item> getAllItems(Long bucketId);

    Bucket create(Bucket bucket);

    Bucket get(Long bucketId);

    Bucket update(Bucket bucket);

    void delete(Long bucketId);
}
