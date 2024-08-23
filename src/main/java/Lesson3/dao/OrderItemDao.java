package Lesson3.dao;

import Lesson3.connection.DBConnection;
import Lesson3.models.Order;
import Lesson3.models.OrderItem;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {

    OrderDao orderDao;

    public OrderItemDao() {
        this.orderDao = new OrderDao();
    }

    public List<OrderItem> getAllOrderItems() throws SQLException {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        String query = "select * from order_items";

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ) {
            while (rs.next()) {
                OrderItem orderItem = new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity")
                );
                orderItems.add(orderItem);
            }
            {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return orderItems;
    }
    public OrderItem getOrderItemById(int id) throws SQLException {
        String query = "select * from order_items where id = " + id;
        OrderItem orderItem = null;

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            if (rs.next() != false) {

                orderItem = new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity")
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return orderItem;
    }
    public boolean addOrderItem(int order_id, String productName, int quantity) throws SQLException {
        String query = "insert into order_items (order_id, product_name, quantity) values (?, ?, ?)";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, order_id);
            stmt.setString(2, productName);
            stmt.setInt(3, quantity);

            if (orderDao.getOrderById(order_id) != null){
                int i = stmt.executeUpdate();
                return i > 0;
            }
            return false;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean updateOrderItem(int id, String productName, int quantity) throws SQLException {

        if (getOrderItemById(id) != null) {

            String query = "update order_items set (product_name, quantity) = (?, ?) where id = ?";

            try (
                    Connection conn = DBConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)
            ) {
                stmt.setString(1, productName);
                stmt.setInt(2, quantity);
                stmt.setInt(3, id);
                int i = stmt.executeUpdate();
                return i > 0;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return false;
    }
    public boolean deleteOrderItem(int id) throws SQLException {

        if (getOrderItemById(id) != null) {

            String query = "delete from order_items where id = ?";

            try (
                    Connection conn = DBConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)
            ) {
                stmt.setInt(1, id);
                int i = stmt.executeUpdate();
                return i > 0;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }





}
