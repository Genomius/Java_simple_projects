package genome.controllers;

import genome.models.Auto;
import genome.models.User;
import genome.services.AutoService;
import genome.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AutoController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AutoService autoService;
    
    @RequestMapping(value = "/users/{user-id}/autos", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView showUserAutos(
            @PathVariable("user-id") Integer id
    ) {
        //UserDao userDao = UserFactory.getInstance().getUserDao();
        //User user = new UserService(userDao).getUserById(id);
        //AutoDao autoDao = AutoFactory.getInstance().getAutoDao();
        //List<Auto> autos = new AutoService(autoDao).getAllAutosByUserId(user.getId());
        
        User user = userService.getUserById(id);
        List<Auto> autos = autoService.getAllAutosByUserId(user.getId());
        
        ModelAndView modelAndView = new ModelAndView("autos");
        modelAndView.addObject("title", user.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("autos", autos);
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/autos", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView showAutos(){
        //AutoDao autoDao = AutoFactory.getInstance().getAutoDao();
        //List<Auto> autos = new AutoService(autoDao).getAllAutos();
        
        List<Auto> autos = autoService.getAllAutos();
        
        ModelAndView modelAndView = new ModelAndView("autos");
        modelAndView.addObject("title", "Автомобили");
        modelAndView.addObject("autos", autos);
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/autos", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    ModelAndView addAutos(
        @PathVariable("model") String model,
        @PathVariable("color") String color,
        @PathVariable("user_id") int userId
    ) {
        //UserDao userDao = UserFactory.getInstance().getUserDao();
        //AutoDao autoDao = AutoFactory.getInstance().getAutoDao();
        //int autoId = new AutoService(autoDao).saveAuto(new Auto(model, color, new UserService(userDao).getUserById(userId)));
        //List<Auto> autos = new AutoService(autoDao).getAllAutos();
        
        int autoId = autoService.saveAuto(new Auto(model, color, userService.getUserById(userId)));
        List<Auto> autos = autoService.getAllAutos();
                
        ModelAndView modelAndView = new ModelAndView("autos");
        modelAndView.addObject("title", "Автомобили");
        modelAndView.addObject("autos", autos);
        
        return modelAndView;
    }
}
