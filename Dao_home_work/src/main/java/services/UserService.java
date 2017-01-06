package services;

import dao.UserDao;
import models.Auto;
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
    
    public boolean haveAuto(User user, AutoService autoService){
        List<Auto> autos = autoService.getAllAutos();
        
        for (Auto auto : autos) {
            if (auto.getUserId() == user.getId())
                return true;
        }
        
        return false;
    }
    
    public List<User> getAllUsers(){
        List<User> users = userDao.findAllUsers();
        
        return users;
    }
    
    public List<User> getAllUsersWithHisAutos(AutoService autoService){
        List<User> users = userDao.findAllUsers();
        List<Auto> autos = autoService.getAllAutos();
    
        for (User user : users) {
            for (Auto auto : autos) {
                if (auto.getUserId() == user.getId())
                    user.addAuto(auto);
            }
            users.add(user);
        }
    
        return users;
    }
    
    public User getUserById(int id){
        // ToDo: Перед вызовом find(id) проверить в БД на EXIST
        User user = userDao.find(id);

        return user;
    }
    
    public User getUserByIdWithAuto(int id, AutoService autoService){
        User user = userDao.find(id);
        List<Auto> autos = autoService.getAllAutos();
        
        for (Auto auto : autos) {
            if (auto.getUserId() == user.getId())
                user.addAuto(auto);
        }
        
        return user;
    }
    
    public User updateUser(User user, User new_user){
        userDao.update(user, new_user);
        
        return user;
    }
}
