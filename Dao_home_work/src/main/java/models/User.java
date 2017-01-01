package models;

import java.util.List;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private int age;
    private List<Auto> autos;
    
    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = -1;
        this.autos = new ArrayList<Auto>();
    }
    
    public void printUserData(){
        System.out.println("#" + this.getId() + " " + this.getName() + " " + this.getAge() + " ");
        if(autos != null)
            for(Auto auto : this.autos)
                System.out.println("\t#" + auto.getId()+ " " + auto.getModel() + " " + auto.getColor());
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
