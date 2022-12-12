package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL = "db.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";


    public static Connection getConnect() {
        try {

            return DriverManager.getConnection(
                    PropertiesUtil.get(URL),
                    PropertiesUtil.get(USERNAME),
                    PropertiesUtil.get(PASSWORD)
                    );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private ConnectionManager() {
    }
}
