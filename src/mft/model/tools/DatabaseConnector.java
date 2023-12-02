package mft.model.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    //  todo : best practice --> Singleton, use connection pool
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "aryan_drv",
                "drvdrv461017"
        );
    }
}
