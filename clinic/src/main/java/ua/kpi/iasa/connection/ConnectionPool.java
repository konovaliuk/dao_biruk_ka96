package ua.kpi.iasa.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static final BasicDataSource dataSource = new BasicDataSource();
    static {
        try (InputStream input = new FileInputStream("src/main/resources/database.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            dataSource.setUrl(prop.getProperty("url"));
            dataSource.setUsername(prop.getProperty("login"));
            dataSource.setPassword(prop.getProperty("password"));
            dataSource.setDriverClassName(prop.getProperty("driver"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ConnectionPool() {}
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

