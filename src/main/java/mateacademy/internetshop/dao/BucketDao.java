package mateacademy.internetshop.dao;

import java.util.List;

import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;

public interface BucketDao {

    Bucket addItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);

    List<Item> getAllItems(Long bucketId);

    void deleteItem(Long bucketId, Long itemId);

    Bucket create(Bucket bucket);

    Bucket get(Long userID);

    Bucket update(Bucket bucket);

    void delete(Long bucketId);
}
