package genome.controllers;

import genome.dao.AutoDao;
import genome.dao.UserDao;
import genome.fabrics.AutoFactory;
import genome.fabrics.UserFactory;
import genome.models.Auto;
import genome.models.User;
import genome.services.AutoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import genome.services.UserService;

import java.util.List;

@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }

    @RequestMapping(value = "/users/{user-id}/autos", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView showUserAutos(
            @PathVariable("user-id") Integer id
    ){
        UserDao userDao = UserFactory.getInstance().getUserDao();
        User user = new UserService(userDao).getUserById(id);
        AutoDao autoDao = AutoFactory.getInstance().getAutoDao();
        List<Auto> autos = new AutoService(autoDao).getAllAutosByUserId(user.getId());
        
        ModelAndView modelAndView = new ModelAndView("autos");
        modelAndView.addObject("title", user.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("autos", autos);
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/autos", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView showAutos(){
        AutoDao autoDao = AutoFactory.getInstance().getAutoDao();
        List<Auto> autos = new AutoService(autoDao).getAllAutos();
        ModelAndView modelAndView = new ModelAndView("autos");
        modelAndView.addObject("title", "Автомобили");
        modelAndView.addObject("autos", autos);
        
        return modelAndView;
    }
}
