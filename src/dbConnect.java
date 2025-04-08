import java.sql.*;

public class dbConnect {
    String url = "jdbc:sqlserver://localhost:1433;databaseName=spicy_noodle_db;encrypt=true;trustServerCertificate=true";
    String user = "sa"; 
    String password = "123456"; 



    public dbConnect() {
        // Constructor
    }
    public void getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Disconnected successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
