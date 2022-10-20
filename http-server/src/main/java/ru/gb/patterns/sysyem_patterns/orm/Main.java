package ru.gb.patterns.sysyem_patterns.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT)");
        UserRepository repository = new UserRepository(connection);

//            statement.executeUpdate("INSERT INTO users (username, password)"
//                    + "VALUES ('A', 'A');");
//            statement.executeUpdate("INSERT INTO users (username, password)"
//                    + "VALUES ('B', 'B');");
//            statement.executeUpdate("INSERT INTO users (username, password)"
//                    + "VALUES ('C', 'C');");
//            statement.executeUpdate("INSERT INTO users (username, password)"
//                    + "VALUES ('D', 'D');");
        repository.beginTransaction();
        repository.insert(new User("A", "A"));
        repository.insert(new User("B", "B"));
        repository.insert(new User("C", "C"));
        repository.insert(new User("D", "D"));
        repository.update(new User("B", "b"));
        repository.delete(new User("B", "B"));
        repository.commit();
        System.out.println(repository.findById(1L).orElseThrow());
    }
}
