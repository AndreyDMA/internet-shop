package mateacademy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static String DB_NAME = "test";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Item create(Item item) {
        Statement statement = null;
        String query = String.format("INSERT INTO %s.items (name, price) VALUES ('%s', '%f');"
                + "", DB_NAME, item.getName(), item.getPrice());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            logger.error("Can't create item " + item.getName());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close statement ", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item get(Long id) {
        Statement statement = null;
        String query = String.format("SELECT * FROM %s.items WHERE item_id = %d;", DB_NAME, id);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item(itemId);
                item.setName(name);
                item.setPrice(price);
                return item;
            }
        } catch (SQLException e) {
            logger.error("Can't get item by id " + id);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close statement ", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        Statement statement = null;
        String query = String.format("UPDATE %s.items SET name = '%s', price = '%f' WHERE "
                + "item_id = %d;", DB_NAME, item.getName(), item.getPrice(), item.getItemId());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            logger.error("Can't update item " + item.getName());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close statement ", e);
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Long itemId) {
        Statement statement = null;
        String query = String.format("DELETE FROM %s.items WHERE item_id = %d;", DB_NAME, itemId);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Can't update item by id" + itemId);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error("Can't close statement ", e);
                }
            }
        }
    }
}
