package controllers;

import dao.UserDao;
import fabrics.UserFactory;
import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

import java.util.List;

@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView showUsers(){
        UserDao userDao = UserFactory.getInstance().getUserDao();
        List<User> users = new UserService(userDao).getAllUsers();
        
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);

        return modelAndView;
    }
}
