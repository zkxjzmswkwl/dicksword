package software.carter.database;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;


// String sql = "CREATE TABLE ACCOUNTS" +
//                 "(ID INTEGER PRIMARY KEY AUTOINCREMENT   NOT NULL, " +
//                 " USERNAME       TEXT   NOT NULL, " +
//                 " PASSWORD       TEXT   NOT NULL, " + 
//                 " PICTURE        TEXT" +
//                 " STATUS         TEXT)";

public class Accounts {
    
    public static void createAccount(String username, String password) {
        // Execution of queries/updates should be abstracted on account of this being VERY silly.
        Connection c = null;
        Statement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt = c.createStatement();


            String sql = String.format(
                """
                INSERT INTO ACCOUNTS (USERNAME, PASSWORD)
                VALUES('%s', '%s')
                """, username, password);

            stmt.execute(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
