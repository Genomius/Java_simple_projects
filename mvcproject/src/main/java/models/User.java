package models;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "age")
    private int age;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Auto> autos;
    
    public User() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public List<Auto> getAutos() {
        return autos;
    }
    
    public void setAutos(ArrayList<Auto> autos) {
        this.autos = autos;
    }
    
    public void addAuto(Auto auto){
        this.autos.add(auto);
    }
}
