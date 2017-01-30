package genome.converters;

import genome.dto.AutoDto;
import genome.dto.UserDto;
import genome.models.Auto;

public class AutoToAutoDtoConverter {
    
    public static AutoDto convertWithoutUser(Auto auto) {
        AutoDto autoDto = new AutoDto();
        autoDto.setId(auto.getId());
        autoDto.setModel(auto.getModel());
        
        return autoDto;
    }
    
    public static AutoDto convertWithUser(Auto auto) {
        AutoDto autoDto = new AutoDto();
        autoDto.setId(auto.getId());
        autoDto.setModel(auto.getModel());
        
        UserDto userDto = UserToUserDtoConverter.convertWithOutAutos(auto.getUser());
        autoDto.setUser(userDto);
        
        return autoDto;
    }
}
