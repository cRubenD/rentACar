import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
        String user = "root";
        String password = "Formula15!";

        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
            //System.out.
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
    }
}
