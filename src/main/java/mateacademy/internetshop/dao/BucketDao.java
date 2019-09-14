package mateacademy.internetshop.dao;

import mateacademy.internetshop.model.Bucket;

public interface BucketDao {

    Bucket create(Bucket bucket);
    Bucket get(Long bucketId);
    Bucket update(Bucket bucket);
    void delete(Long bucketId);
}
