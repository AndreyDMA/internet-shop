package mateacademy.internetshop.dao.daoimpl;

import java.util.List;
import java.util.NoSuchElementException;

import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.db.Storage;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;

@Dao
public class BucketDaoImpl implements BucketDao {
    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = get(bucketId);
        Item item = itemDao.get(itemId);
        bucket.getItems().add(item);
        return update(bucket);
    }

    @Override
    public Bucket clear(Long bucketId) {
        Bucket bucket = get(bucketId);
        bucket.getItems().clear();
        return bucket;
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        Bucket bucket = get(bucketId);
        return bucket.getItems();
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
        Bucket bucket = get(bucketId);
        bucket.getItems().removeIf(i -> i.getItemId().equals(itemId));
    }

    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long userId) {
        return Storage.buckets.stream()
                .filter(b -> b.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with id " + userId));
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (Storage.buckets.get(i).getBucketId().equals(bucket.getBucketId())) {
                Storage.buckets.set(i, bucket);
            }
        }
        return bucket;
    }

    @Override
    public void delete(Long bucketId) {
        Storage.buckets.removeIf(b -> b.getBucketId().equals(bucketId));
    }
}
