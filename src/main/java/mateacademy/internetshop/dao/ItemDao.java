package mateacademy.internetshop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mateacademy.internetshop.model.Item;

public interface ItemDao {

    List<Item> getAll();

    Item initItem(ResultSet resultSet) throws SQLException;

    Item create(Item item);

    Item get(Long itemId);

    Item update(Item item);

    void delete(Long itemId);
}
