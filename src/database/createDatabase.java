package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class createDatabase {
    public void executeSqlFile(String filePath) {
        StringBuilder sqlBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                sqlBuilder.append(line).append("\n");
            }

            String[] sqlStatements = sqlBuilder.toString().split(";");

            Connection conn = dbConnect.getConnection();  
            if (conn == null) {
                System.out.println("Failed to connect to the database.");
                return;
            }

            Statement stmt = conn.createStatement();

            for (String sql : sqlStatements) {
                sql = sql.trim();
                if (!sql.isEmpty()) {
                    stmt.execute(sql);
                }
            }

            System.out.println("SQL script executed successfully.");
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createDatabase dbCreator = new createDatabase();
        System.out.println("Working directory: " + System.getProperty("user.dir"));

        dbCreator.executeSqlFile(System.getProperty("user.dir")+ "/src/database/database.sql");  
    }
}
