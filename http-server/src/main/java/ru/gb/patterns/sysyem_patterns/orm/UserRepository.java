package ru.gb.patterns.sysyem_patterns.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {
    private final Connection connection;
    private final UserMapper mapper;
    private UnitOfWork unitOfWork;

    public UserRepository(Connection connection) {
        this.connection = connection;
        this.mapper = new UserMapper(connection);
        this.unitOfWork = new UnitOfWork(connection);
    }

    public Optional<User> findById(Long id){
        return mapper.findById(id);
    }

    public void beginTransaction(){
        this.unitOfWork = new UnitOfWork(connection);
    }

    public void insert(User user){
        unitOfWork.registerNew(user);
    }
    public void update(User user){
        unitOfWork.registerUpdate(user);
    }
    public void delete(User user){
        unitOfWork.registerDelete(user);
    }
    public void commit() throws SQLException {
        unitOfWork.commit();
    }
}
