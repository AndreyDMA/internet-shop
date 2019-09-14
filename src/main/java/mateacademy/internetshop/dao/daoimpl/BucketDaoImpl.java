package mateacademy.internetshop.dao.daoimpl;

import java.util.NoSuchElementException;

import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.db.Storage;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        return Storage.buckets.stream()
                .filter(b -> b.getId().equals(bucketId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with id " + bucketId));
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (Storage.buckets.get(i).getId().equals(bucket.getId())) {
                Storage.buckets.set(i, bucket);
            }
        }
        return bucket;
    }

    @Override
    public void delete(Long bucketId) {
        Storage.buckets.removeIf(b -> b.getId().equals(bucketId));
    }
}
