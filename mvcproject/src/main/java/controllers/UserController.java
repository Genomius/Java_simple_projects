package controllers;

import dao.UserDao;
import fabrics.UserFactory;
import models.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDao userDao = UserFactory.getInstance().getUserDao();
        List<User> users = new UserService(userDao).getAllUsers();
        
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("title", "Пользователи");
        modelAndView.addObject("users", users);
        
        return modelAndView;
    }
}
