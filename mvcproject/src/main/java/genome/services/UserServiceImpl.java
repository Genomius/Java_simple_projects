package genome.services;

import genome.dao.UserDao;
import genome.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserDao userDao;
    
    public boolean isRegistered(String name){
        List<User> users = userDao.findAll();
        
        for(User user : users)
            if(user.getName().equals(name))
                return true;
        
        return false;
    }
    
    public List<User> getAllUsers(){
        return userDao.findAll();
    }
    
    public User getUserById(int id){
        return userDao.find(id);
    }
    
    public int updateUser(int userId, User newUser){
        return userDao.update(userId, newUser);
    }
    
    public int saveUser(User user){return userDao.save(user);}
    
    public boolean deleteUserById(int userId){
        return userDao.delete(userId);
    }
}
