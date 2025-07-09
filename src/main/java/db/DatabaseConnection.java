package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection conn = null;
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "ciau";

//    static {
//        try {
//            // Explicitly load the PostgreSQL JDBC driver
//            Class.forName("org.postgresql.Driver");
//            System.out.println("Driver loaded");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to load PostgreSQL JDBC driver", e);
//        }
//    }

    // Singleton pattern
    public static Connection getInstance() throws SQLException {
        if(conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }
}
