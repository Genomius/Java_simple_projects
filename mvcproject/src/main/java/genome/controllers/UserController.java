package genome.controllers;

import genome.models.User;
import genome.services.UserService;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import javax.ws.rs.PathParam;
import java.util.List;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView showUsers() {;
        List<User> users = userService.getAllUsers();
        
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("title", "Пользователи");
        modelAndView.addObject("users", users);
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/users/{user-id}", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView showUser(
            @PathVariable("user-id") Integer id
    ) {
        User user = userService.getUserById(id);
        
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("title", user.getName());
        modelAndView.addObject("user", user);
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    ModelAndView addUser(
            @RequestParam("name") String name,
            @RequestParam("age") int age
    ) {
        int id = userService.saveUser(new User(name, age));
        List<User> users = userService.getAllUsers();
    
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("title", "Пользователи");
        modelAndView.addObject("users", users);
    
        return modelAndView;
    }
    
}
    