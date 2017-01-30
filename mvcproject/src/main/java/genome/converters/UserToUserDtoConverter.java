package genome.converters;

import genome.dto.AutoDto;
import genome.dto.UserDto;
import genome.models.Auto;
import genome.models.User;

import java.util.ArrayList;

public class UserToUserDtoConverter {
    public static UserDto convertWithOutAutos(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        
        return userDto;
    }
    
    public static UserDto convertWithAutos(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setAutos(new ArrayList<AutoDto>());
    
        for (Auto auto: user.getAutos()) {
            AutoDto autoDto = AutoToAutoDtoConverter.convertWithoutUser(auto);
            userDto.getAutos().add(autoDto);
        }
        
        return userDto;
    }
}
