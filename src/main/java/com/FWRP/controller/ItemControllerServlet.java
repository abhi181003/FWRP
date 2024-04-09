package com.FWRP.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.FWRP.model.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/items")
public class ItemControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtain a connection from the database connection pool
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/FWRP");
            conn = ds.getConnection();

            // Execute a query to retrieve items from the database
            String sql = "SELECT id, name, description, price FROM item";  // Fetch specific columns
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Process the result set and populate itemList
            List<Item> itemList = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                itemList.add(item);
            }

            // Set itemList as a request attribute and forward to items.jsp
            request.setAttribute("itemList", itemList);
            request.getRequestDispatcher("items.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();  // Handle or log the exception
        } finally {
            // Close resources in a finally block to ensure they are released
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
