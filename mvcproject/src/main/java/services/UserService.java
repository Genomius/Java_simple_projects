package services;

import dao.UserDao;
import models.Auto;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDao userDao;
    
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    
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
