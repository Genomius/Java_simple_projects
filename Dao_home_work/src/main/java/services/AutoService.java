package services;

import dao.AutoDao;
import models.Auto;
import models.User;

import java.util.List;

public class AutoService {
    AutoDao autoDao;
    
    public AutoService(AutoDao autoDao) {
        this.autoDao = autoDao;
    }
    
    public User getUserByAuto(Auto auto, UserService userService){
        if(auto == null){
            System.out.println("Не верные данные auto");
            return null;
        }
        List<User> users = userService.getAllUsers();
        
        for (User user : users) {
            if (auto.getUserId() == user.getId())
                return user;
        }
        return null;
    }
    
    public List<Auto> getAllAutos(){
        return autoDao.findAllAutos();
    }
    
    public Auto getAutoById(int id){
        return autoDao.find(id);
    }
}
