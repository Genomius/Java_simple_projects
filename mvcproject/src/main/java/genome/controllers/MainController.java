package genome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }
    
    @RequestMapping("favicon.ico")
    @ResponseBody
    void favicon() {}
}