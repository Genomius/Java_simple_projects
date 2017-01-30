package genome.services;

import genome.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    int saveUser(User user);
    boolean isRegistered(String name);
    User getUserById(int id);
    int updateUser(int userId, User newUser);
    boolean deleteUserById(int userId);
}
