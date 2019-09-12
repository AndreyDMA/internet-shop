package mateacademy.internetshop.service;

import mateacademy.internetshop.model.Bucket;

public interface BucketService {

    Bucket addItem(Long bucketId, Long itemId);

    Bucket create(Bucket bucket);

    Bucket get(Long bucketId);

    Bucket update(Bucket bucket);

    Bucket delete(Long bucketId);
}
