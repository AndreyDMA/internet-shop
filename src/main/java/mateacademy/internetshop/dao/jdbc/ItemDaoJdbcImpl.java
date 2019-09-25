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
        Statement stmt = null;
        String query = "INSERT INTO " + DB_NAME + ".items (name, price) "
                + "VALUES ('" + item.getName() + "', '" + item.getPrice() + "');";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            logger.warn("Can't create item " + item.getName());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement ", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item get(Long id) {
        Statement stmt = null;
        String query = "SELECT * FROM " + DB_NAME + ".items where item_id = " + id + ";";
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                long itemId = rs.getLong("item_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                Item item = new Item(itemId);
                item.setName(name);
                item.setPrice(price);
                return item;
            }
        } catch (SQLException e) {
            logger.warn("Can't get item by id " + id);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement ", e);
                }
            }
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        Statement stmt = null;
        String query = "UPDATE " + DB_NAME + ".items SET name = '"
                + item.getName() + "', price = " + item.getPrice()
                + " WHERE (item_id = " + item.getItemId() + ");";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return item;
        } catch (SQLException e) {
            logger.warn("Can't update item " + item.getName());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement ", e);
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Long itemId) {
        Statement stmt = null;
        String query = "DELETE FROM " + DB_NAME
                + ".items WHERE (item_id = " + itemId + ");";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.warn("Can't update item by id" + itemId);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.warn("Can't close statement ", e);
                }
            }
        }
    }
}
