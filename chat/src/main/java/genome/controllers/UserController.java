package genome.controllers;

import genome.dto.UserDto;
import genome.dto.UserForRegistrationDto;
import genome.models.User;
import genome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<UserDto> signUp(
            @RequestBody UserForRegistrationDto user
    ){
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> signIn(
            @RequestHeader("password") String password,
            @RequestHeader("login") String login
    ){
        String token = userService.login(login, password);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Auth-Token", token);
        
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }
}
