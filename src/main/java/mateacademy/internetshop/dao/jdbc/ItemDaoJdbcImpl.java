package mateacademy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Item> getAll() {
        String query = "SELECT * FROM items;";
        List<Item> itemsList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(itemId, name, price);
                itemsList.add(item);
            }
            return itemsList;
        } catch (SQLException e) {
            logger.error("Can't get list of items ", e);
        }
        return null;
    }

    @Override
    public Item create(Item item) {
        String query = "INSERT INTO items (name, price) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            logger.error("Can't create item " + item.getName(), e);
        }
        return null;
    }

    @Override
    public Item get(Long itemId) {
        String query = "SELECT * FROM items WHERE item_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                return new Item(itemId, name, price);
            }
        } catch (SQLException e) {
            logger.error("Can't get item by id " + itemId, e);
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE items SET name = ?, price = ? WHERE item_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setLong(3, item.getItemId());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            logger.error("Can't update item " + item.getName(), e);
        }
        return null;
    }

    @Override
    public void delete(Long itemId) {
        String query = "DELETE FROM items WHERE item_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete item by id" + itemId, e);
        }
    }
}
