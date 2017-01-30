package genome.controllers.rest;

import genome.converters.UserToUserDtoConverter;
import genome.dto.UserDto;
import genome.models.User;
import genome.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestUserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDto> getUsers(){
        List<UserDto> resultUsers = new ArrayList<UserDto>();
        List<User> users = userService.getAllUsers();
    
        for (User user : users) {
            resultUsers.add(UserToUserDtoConverter.convertWithAutos(user));
        }
        
        return resultUsers;
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<List<UserDto>> addUser(
        @RequestBody UserDto userDto
    ){
        User newUser = new User(userDto.getName(), userDto.getAge());
        userService.saveUser(newUser);
    
        List<UserDto> resultUsers = new ArrayList<UserDto>();
        List<User> users = userService.getAllUsers();
    
        for (User user : users) {
            resultUsers.add(UserToUserDtoConverter.convertWithAutos(user));
        }
        
        ResponseEntity<List<UserDto>> response = new ResponseEntity<List<UserDto>>(resultUsers, HttpStatus.CREATED);
        
        return response;
    }
}
