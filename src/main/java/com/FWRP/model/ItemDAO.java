package com.FWRP.model;

//ItemDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FWRP.DBconnection.DBConnector;


public class ItemDAO {
 public List<Item> getItems() {
     List<Item> itemList = new ArrayList<>();
     String query = "SELECT id, name, description, price FROM Item";

     try (Connection connection = DBConnector.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          ResultSet resultSet = preparedStatement.executeQuery()) {

         while (resultSet.next()) {
             int id = resultSet.getInt("id");
             String name = resultSet.getString("name");
             String description = resultSet.getString("description");
             double price = resultSet.getDouble("price");

             Item item = new Item(id, name, description, price);
             itemList.add(item);
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }

     return itemList;
 }
}
