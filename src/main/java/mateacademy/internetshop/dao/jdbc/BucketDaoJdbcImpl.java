package mateacademy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;

import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        String query = "INSERT INTO buckets_items (bucket_id, item_id) "
                + "VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.setLong(2, itemId);
            statement.executeUpdate();
            return new Bucket(bucketId);
        } catch (SQLException e) {
            logger.error("Can't add  item by id" + itemId);
        }
        return null;
    }

    @Override
    public Bucket clear(Long bucketId) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
            return new Bucket(bucketId);
        } catch (SQLException e) {
            logger.error("Can't clear bucket by id" + bucketId);
        }
        return null;
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        String query = "SELECT buckets.bucket_id, buckets.user_id, "
                + "items.item_id, items.name, items.price "
                + "FROM buckets "
                + "INNER JOIN buckets_items ON buckets.bucket_id = buckets_items.bucket_id "
                + "INNER JOIN items ON buckets_items.item_id = items.item_id "
                + "WHERE buckets.bucket_id = ?;";
        List<Item> itemsList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item(itemId, name, price);
                itemsList.add(item);
            }
            return itemsList;
        } catch (SQLException e) {
            logger.error("Can't get bucket by id " + bucketId, e);
        }
        return null;
    }

    @Override
    public void deleteItem(Long bucketId, Long itemId) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ? AND item_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.setLong(2, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete item by id" + itemId);
        }
    }

    @Override
    public Bucket create(Bucket bucket) {
        String query = "INSERT INTO buckets (user_id) VALUES (?);";
        try (PreparedStatement statement =
                     connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, bucket.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                bucket.setBucketId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Can't create bucket " + bucket.getBucketId());
        }
        return null;
    }

    @Override
    public Bucket get(Long userId) {
        String query = "SELECT * FROM buckets WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long bucketId = resultSet.getLong("bucket_id");
                Bucket bucket = new Bucket(userId);
                bucket.setBucketId(bucketId);
                return bucket;
            }
        } catch (SQLException e) {
            logger.error("Can't get bucket by user id " + userId, e);
        }
        return null;
    }

    @Override
    public Bucket update(Bucket bucket) {
        String query = "UPDATE buckets SET user_id = ? WHERE bucket_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucket.getUserId());
            statement.setLong(2, bucket.getBucketId());
            statement.executeUpdate();
            return bucket;
        } catch (SQLException e) {
            logger.error("Can't update bucket by id " + bucket.getBucketId());
        }
        return null;
    }

    @Override
    public void delete(Long bucketId) {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ?;"
                + "DELETE FROM buckets WHERE bucket_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete bucket by id" + bucketId);
        }
    }
}
