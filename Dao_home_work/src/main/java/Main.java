import dao.AutoDao;
import dao.UserDao;
import factories.AutoFactory;
import factories.UserFactory;
import models.Auto;
import models.User;
import services.UserService;
import services.AutoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // ToDo: Добавить spring jdbc
        UserDao userDao = UserFactory.getInstance().getUserDao();
        AutoDao autoDao = AutoFactory.getInstance().getAutoDao();
        UserService userService = new UserService(userDao);
        AutoService autoService = new AutoService(autoDao);
        User user;
        Auto auto;
        
    
        user = userService.getUserById(3);
        if(user != null)
            user.printUserData();
        else
            System.out.println("Такого юзера не существует!");
        
        auto = autoService.getAutoById(5);
        if(auto != null)
            auto.printAutoData();
        else
            System.out.println("Такого автомобиля не существует!");
        
        user = autoService.getUserByAuto(auto, userService);
        if(user != null)
            user.printUserData();
        else
            System.out.println("Такого юзера не существует!");
        
        user = userService.updateUser(user, new User("Vitek", 55));
        if(user != null)
            user.printUserData();
        else
            System.out.println("Такого юзера не существует!");
    }
}