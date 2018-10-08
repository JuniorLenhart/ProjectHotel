package hotel.support;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionReport {

    private static ConnectionReport instance = null;
    private Connection connection = null;

    public ConnectionReport() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("db.properties"));
            String dbdriver = prop.getProperty("db.driver");
            String dburl = prop.getProperty("db.url");
            String dbuser = prop.getProperty("db.user");
            String dbsenha = "postgres";

            Class.forName(dbdriver);

            if (dbuser.length() != 0) {
                connection = DriverManager.getConnection(dburl, dbuser, dbsenha);
            } else {
                connection = DriverManager.getConnection(dburl);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static ConnectionReport getInstance() {
        if (instance == null) {
            instance = new ConnectionReport();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            throw new RuntimeException("conexao == null");
        }
        return connection;
    }

    public void shutDown() {
        try {
            connection.close();
            instance = null;
            connection = null;
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
