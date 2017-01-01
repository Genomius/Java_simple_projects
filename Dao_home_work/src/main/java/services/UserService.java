package services;

import dao.UserDao;
import models.User;

import java.util.List;

public class UserService {
    private UserDao userDao;
    
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public boolean isRegistered(String name){
        List<User> users = userDao.findAllUsers();
        for(User user : users){
            if(user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    public boolean haveAuto(int id){
        return false;
    }
    
    
}
