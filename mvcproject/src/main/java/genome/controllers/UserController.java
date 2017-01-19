package genome.controllers;

import genome.dao.UserDao;
import genome.fabrics.UserFactory;
import genome.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import genome.services.UserService;
import java.util.List;


@Controller
public class UserController {
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView showUsers(){
        UserDao userDao = UserFactory.getInstance().getUserDao();
        List<User> users = new UserService(userDao).getAllUsers();
        
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("title", "Пользователи");
        modelAndView.addObject("users", users);
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/users/{user-id}", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView showUser(
            @PathVariable("user-id") Integer id
    ){
        UserDao userDao = UserFactory.getInstance().getUserDao();
        User user = new UserService(userDao).getUserById(id);
        
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("title", user.getName());
        modelAndView.addObject("user", user);
        
        return modelAndView;
    }
    