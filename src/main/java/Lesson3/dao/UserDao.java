package Lesson3.dao;

import Lesson3.connection.DBConnection;
import Lesson3.models.User;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<User>();
        String query = "select * from users";

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ) {
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"));
                users.add(user);
            }
            {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;

    }

    public User getUserById(int id) throws SQLException {
        String query = "select * from users where id = " + id;
        User user = null;

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            if (rs.next() != false) {

                user = new User(rs.getInt("id"), rs.getString("name"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean addUser(String name) throws SQLException {
        String query = "insert into users (name) values (?)";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, name);
            int i = stmt.executeUpdate();
            return i > 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUser(int id, String name) throws SQLException {

        if (getUserById(id) != null) {

            String query = "update users set name = ? where id = ?";

            try (
                    Connection conn = DBConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)
            ) {
                stmt.setString(1, name);
                stmt.setInt(2, id);
                int i = stmt.executeUpdate();
                return i > 0;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return false;
    }

    public boolean deleteUser(int id) throws SQLException {

        if (getUserById(id) != null) {

            String query = "delete from users where id = ?";

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
            catch (PSQLException ex) {
                System.out.println("This user is linked to other tables, in order to delete it, you must first remove it from other tables");
            }
        }
        return false;
    }
}
