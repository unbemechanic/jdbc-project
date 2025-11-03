package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
//    private static final String FILE = "config.properties";

    public static Connection getConnection() {
        Properties props = new Properties();
        try (InputStream FILE = Util.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(FILE);
            String URL = props.getProperty("db.url");
            String USERNAME = props.getProperty("db.user");
            String PASSWORD = props.getProperty("db.password");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database", e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
