package mateacademy.internetshop.service.impl;

import java.util.List;

import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.lib.Service;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;
    @Inject
    private static ItemDao itemDao;

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
        bucketDao.deleteItem(bucketId, itemId);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        return bucketDao.addItem(bucketId, itemId);
    }

    @Override
    public Bucket clear(Long bucketId) {
        return bucketDao.clear(bucketId);
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return bucketDao.getAllItems(bucketId);
    }

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long bucketId) {
        return bucketDao.get(bucketId);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Long bucketId) {
        bucketDao.delete(bucketId);
    }
}
