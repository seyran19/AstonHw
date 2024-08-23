package Lesson3.connection;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() throws IOException, SQLException {

        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL driver not found", e);
        }

        Properties prop = new Properties();
        InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
        prop.load(input);

        String url = prop.getProperty("jdbc.url");
        String username = prop.getProperty("jdbc.username");
        String password = prop.getProperty("jdbc.password");

        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}

