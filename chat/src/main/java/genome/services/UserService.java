package genome.services;

import genome.dto.UserDto;
import genome.dto.UserForRegistrationDto;
import genome.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User findByLogin(String login);
    User save(User user);
    
    boolean isRegistered(String login);
    String getGeneratedMd5Hash(String password);
    String tokenGeneration(String login, String password);
    
    UserDto registerUser(UserForRegistrationDto user);
    String login(String login, String password);
    User findByToken(String token);
}
