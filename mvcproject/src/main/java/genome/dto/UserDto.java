package genome.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import genome.dto.AutoDto;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;
    private String name;
    private Integer age;
    private List<AutoDto> autos;
    
    public UserDto() {
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<AutoDto> getAutos() {
        return autos;
    }
    
    public void setAutos(List<AutoDto> autos) {
        this.autos = autos;
    }
}
