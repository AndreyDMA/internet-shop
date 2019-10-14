package mateacademy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.dao.OrderDao;
import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;

import mateacademy.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    @Inject
    private static UserDao userDao;
    @Inject
    private static ItemDao itemDao;

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        Long orderId = null;
        String addOrderQuery = "insert into orders (user_id) values (?);";
        try (PreparedStatement statement = connection.prepareStatement(addOrderQuery,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUser().getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                orderId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("Can't create order " + orderId, e);
        }
        String fillOrderQuery = "INSERT INTO orders_items (order_id, item_id)"
                + " VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(fillOrderQuery)) {
            statement.setLong(1, orderId);
            for (Item item : order.getItems()) {
                statement.setLong(2, item.getItemId());
                statement.execute();
            }
            return new Order(orderId, order.getUser(), order.getItems());
        } catch (SQLException e) {
            logger.error("Can't create order " + orderId, e);
        }
        return null;
    }

    @Override
    public List<Order> getAllOrdersOfUser(User user) {
        String query = "SELECT orders.order_id, orders.user_id, "
                + "items.item_id, items.name, items.price "
                + "FROM orders "
                + "INNER JOIN orders_items ON orders.order_id = orders_items.order_id "
                + "INNER JOIN items ON orders_items.item_id = items.item_id "
                + "WHERE orders.user_id = ?;";
        List<Order> ordersList = new ArrayList<>();
        List<Item> itemsList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long orderId = resultSet.getLong("order_id");
                Long itemId = resultSet.getLong("item_id");
                Item item = itemDao.get(itemId);
                itemsList.add(item);
                Order order = new Order(orderId, user, itemsList);
                ordersList.add(order);
            }
            return ordersList;
        } catch (SQLException e) {
            logger.error("Can't get list of orders for user with id " + user.getUserId(), e);
        }
        return null;
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order order = new Order(items, user);
        Order newOrder = create(order);
        getAllOrdersOfUser(user).add(newOrder);
        return newOrder;
    }

    @Override
    public void deleteUserOrder(User user, Long orderId) {
        Order order = get(orderId);
        delete(orderId);
        getAllOrdersOfUser(user).remove(order);
    }

    @Override
    public Order get(Long orderId) {
        String query = "SELECT orders.order_id, orders.user_id, "
                + "items.item_id, items.name, items.price "
                + "FROM orders "
                + "INNER JOIN orders_items ON orders.order_id = orders_items.order_id "
                + "INNER JOIN items ON orders_items.item_id = items.item_id "
                + "WHERE orders.order_id = ?;";
        List<Item> itemsList = new ArrayList<>();
        Long userId = 0L;
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getLong("user_id");
                user = userDao.get(userId);
                Long itemId = resultSet.getLong("item_id");
                Item item = itemDao.get(itemId);
                itemsList.add(item);
            }
            return new Order(orderId, user, itemsList);
        } catch (SQLException e) {
            logger.error("Can't get order by id " + orderId, e);
        }
        return null;
    }

    @Override
    public Order update(Order order) {
        String query = "UPDATE orders SET user_id = ? WHERE order_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getUser().getUserId());
            statement.setLong(2, order.getOrderId());
            statement.executeUpdate();
            return order;
        } catch (SQLException e) {
            logger.error("Can't update order by id " + order.getOrderId(), e);
        }
        return null;
    }

    @Override
    public void delete(Long orderId) {
        String deleteItemQquery = "DELETE FROM orders_items WHERE order_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteItemQquery)) {
            statement.setLong(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete items from order " + orderId);
        }
        String deleteOrderQquery = "DELETE FROM orders WHERE order_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteOrderQquery)) {
            statement.setLong(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete items from order " + orderId, e);
        }
    }
}
