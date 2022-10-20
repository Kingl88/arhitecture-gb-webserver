package ru.gb.patterns.sysyem_patterns.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    private DBConnection(){}
    public static Connection getConnection() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        }
        return connection;
    }
}
