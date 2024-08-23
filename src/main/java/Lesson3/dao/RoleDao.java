package Lesson3.dao;

import Lesson3.connection.DBConnection;
import Lesson3.models.Role;
import Lesson3.models.User;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RoleDao {

    public List<Role> getAllRoles() throws SQLException {
        List<Role> roles = new ArrayList<Role>();
        String query = "select * from roles";

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ) {
            while (rs.next()) {
                Role role = new Role(rs.getInt("id"), rs.getString("role_name"));
                roles.add(role);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }

    public Role getRoleById(int id) throws SQLException {
        String query = "select * from roles where id = " + id;
        Role role = null;

        try (
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            if (rs.next() != false) {

                role = new Role(rs.getInt("id"), rs.getString("role_name"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return role;
    }

    public boolean addRole(String name) throws SQLException {
        String query = "insert into roles (role_name) values (?)";

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

    public boolean updateRole(int id, String roleName) throws SQLException {

        if (getRoleById(id) != null) {

            String query = "update roles set role_name = ? where id = ?";

            try (
                    Connection conn = DBConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)
            ) {
                stmt.setString(1, roleName);
                stmt.setInt(2, id);
                int i = stmt.executeUpdate();
                return i > 0;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return false;
    }

    public boolean deleteRole(int id) throws SQLException {

        if (getRoleById(id) != null) {

            String query = "delete from roles where id = ?";

            try (
                    Connection conn = DBConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)
            ) {
                stmt.setInt(1, id);
                int i = stmt.executeUpdate();
                return i > 0;

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (PSQLException ex) {
                System.out.println("This role is linked to other tables, in order to delete it, you must first remove it from other tables");
            }
        }
        return false;
    }

}
