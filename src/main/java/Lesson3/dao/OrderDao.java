package Lesson3.dao;

import Lesson3.connection.DBConnection;
import Lesson3.models.Order;



import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private UserDao userDao;

    public OrderDao() {
        this.userDao = new UserDao();
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<Order>();
        String query = "select * from orders";

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ) {
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getInt("user_id"));
                orders.add(order);
            }
            {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return orders;

    }
    public Order getOrderById(int id) throws SQLException {
        String query = "select * from orders where id = " + id;
        Order order = null;

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            if (rs.next() != false) {

                order = new Order(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getInt("user_id"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
    public boolean addOrder(int user_id, String description) throws SQLException {
        String query = "insert into orders (description, user_id) values (?, ?)";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, description);
            stmt.setInt(2, user_id);

            if (userDao.getUserById(user_id) != null){
                int i = stmt.executeUpdate();
                return i > 0;
            }
            return false;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean updateOrder(int id, String description) throws SQLException {

        if (getOrderById(id) != null) {

            String query = "update orders set description = ? where id = ?";

            try (
                    Connection conn = DBConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)
            ) {
                stmt.setString(1, description);
                stmt.setInt(2, id);
                int i = stmt.executeUpdate();
                return i > 0;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return false;
    }
    public boolean deleteOrder(int id) throws SQLException {

        if (getOrderById(id) != null) {

            String query = "delete from orders where id = ?";

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
