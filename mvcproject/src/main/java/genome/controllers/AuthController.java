package genome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    @RequestMapping(value = "/auth", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView auth(){
        ModelAndView modelAndView = new ModelAndView("auth");
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    ModelAndView login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        ModelAndView modelAndView = new ModelAndView("auth");
        System.out.println("LOGIN SUCCESS");
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/auth/registration", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody
    ModelAndView registration(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        ModelAndView modelAndView = new ModelAndView("auth");
        System.out.println("LOGIN REGISTRATION");
    
        return modelAndView;
    }
}
