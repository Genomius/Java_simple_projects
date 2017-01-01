package dao;

import models.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers();
    User find(int id);
    boolean save(User user);
    boolean update(User user, User new_user);
    boolean delete(int id);
    
}
