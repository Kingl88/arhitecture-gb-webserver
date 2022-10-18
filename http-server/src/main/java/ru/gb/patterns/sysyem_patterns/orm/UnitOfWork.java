package ru.gb.patterns.sysyem_patterns.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {

    private final Connection connection;
    private final List<User> newUsers = new ArrayList<>();
    private final List<User> updateUser = new ArrayList<>();
    private final List<User> deleteUser = new ArrayList<>();

    public UnitOfWork(Connection connection) {
        this.connection = connection;
    }

    public void registerNew(User user){
        this.newUsers.add(user);
    }

    public void registerUpdate(User user){
        this.updateUser.add(user);
    }

    public void registerDelete(User user){
        this.deleteUser.add(user);
    }
    
    public void commit() throws SQLException {
        insert();
        update();
        delete();
    }

    private void delete() throws SQLException {
        for (User user : deleteUser) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE username = '" + user.getLogin() + "';");
        }
    }

    private void update() throws SQLException {
        for (User user : updateUser) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE users SET password = '" + user.getPassword() + "' WHERE username = '" + user.getLogin() + "';");
        }
    }

    private void insert() throws SQLException {
        for (User user : newUsers) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users (username, password)"
                    + "VALUES ('" + user.getLogin() + "','" + user.getPassword() + "');");
        }

    }
}
