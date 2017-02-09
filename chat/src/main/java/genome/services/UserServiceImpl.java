package genome.services;

import genome.dao.UserDao;
import genome.dto.UserDto;
import genome.dto.UserForRegistrationDto;
import genome.models.User;
import genome.security.utils.TokenGenerator;
import static genome.converters.UserConverter.convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private TokenGenerator tokenGenerator;
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public List<User> findAll() {
        return userDao.findAll();
    }
    
    public User findById(Long id) {
        return userDao.findById(id);
    }
    
    public User save(User user) {
        return userDao.save(user);
    }
    
    public User findByLogin(String login){
        return userDao.findByLogin(login);
    }
    
    public boolean isRegistered(String login){
        return findByLogin(login).getLogin().equals(login);
    }
    
    public String getGeneratedMd5Hash(String password){
        byte[] bytesOfMessage = new byte[0];
        MessageDigest md = null;
        
        try {
            bytesOfMessage = password.getBytes("UTF-8");
            md = MessageDigest.getInstance("MD5");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    
        assert md != null;
        return  Arrays.toString(md.digest(bytesOfMessage));
    }
    
    public String tokenGeneration(String login, String password){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        
        return Arrays.toString(bytes);
    }
    
    @Override
    public UserDto registerUser(UserForRegistrationDto user) {
        User newUser = convert(user,
            dto -> new User(
                dto.getLogin(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getName(),
                dto.getSurname()
            )
        );
        
        User savedUser = userDao.save(newUser);
        
        return convert(savedUser,
            model -> new UserDto(
                model.getId(),
                model.getName(),
                model.getSurname()
            )
        );
    }
    
    @Override
    public String login(String login, String password) {
        User registeredUser = userDao.findByLogin(login);
    
        if (registeredUser != null){
            if (passwordEncoder.matches(password, registeredUser.getPassword())) {
                String token = tokenGenerator.generateToken();
                //ToDo: userDao.updateToken(registeredUser.getId(), token);
                userDao.updateToken(registeredUser.getId(), "NIotGxwxvjpTqF13RMry");
                //userDao.updateToken(registeredUser.getId(), "NIotGxwxvjpTqF13RMry2");
    
    
                System.out.println("Авторизация пользователя '" + registeredUser.getLogin() + "' произошла успешно !");
                
                return token;
            } else throw new IllegalArgumentException("Invalid password");
        } else throw new IllegalArgumentException("Invalid login");
    }
    
    @Override
    public User findByToken(String token) {
        return userDao.findByToken(token);
    }
}
