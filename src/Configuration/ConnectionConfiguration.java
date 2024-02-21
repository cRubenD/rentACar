package Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
    private static final String user = "root";
    private static final String password = "Formula15!";

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
            //System.out.
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
        return connection;
    }
}
