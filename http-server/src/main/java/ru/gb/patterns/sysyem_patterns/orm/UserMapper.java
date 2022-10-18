package ru.gb.patterns.sysyem_patterns.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {
    private final Connection connection;
    private final PreparedStatement selectUser;
    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(Connection connection) {
        this.connection = connection;
        try {
            this.selectUser = connection.prepareStatement("select username, password from users where id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<User> findById(long id){
        User user = identityMap.get(id);
        if(user != null){
            return Optional.of(user);
        }
        try {
            selectUser.setLong(1, id);
            ResultSet rs = selectUser.executeQuery();
            if(rs.next()){
                user = new User(rs.getString(1), rs.getString(2));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
