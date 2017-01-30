package genome.dao;

import genome.models.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(Long id);
    User findByLogin(String login);
    User findByToken(String token);
    User save(User user);
    boolean delete(Long id);
    boolean update(User user);
    
    void updateToken(Long id, String token);
    boolean isExistToken(String token);
}
