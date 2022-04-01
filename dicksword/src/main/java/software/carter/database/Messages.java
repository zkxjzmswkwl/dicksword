package software.carter.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Messages {

    public static void createTable() {
        // Execution of queries/updates should be abstracted on account of this being VERY silly.
        Connection c = null;
        Statement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt = c.createStatement();


            // TODO: Nuke database when not currently lazy as fuck and rename table to `MESSAGES`.
            String sql = 
                """
                CREATE TABLE MSGS 
                (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                 sender     INTEGER,
                 sentTo     INTEGER,
                 contents   TEXT,
                 FOREIGN KEY(sender) REFERENCES ACCOUNTS(ACCOUNTSID),
                 FOREIGN KEY(sentTo) REFERENCES ACCOUNTS(ACCOUNTSID));
                """;

            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertMessage(int sender, int sentTo, String message) {
        // Execution of queries/updates should be abstracted on account of this being VERY silly.
        Connection c = null;
        Statement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt = c.createStatement();


            // TODO: Nuke database when not currently lazy as fuck and rename table to `MESSAGES`.
            String sql = String.format(
                """
                INSERT INTO MSGS (sender, sentTo, contents)
                VALUES(%d, %d, '%s')
                """, sender, sentTo, message);

            stmt.execute(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
