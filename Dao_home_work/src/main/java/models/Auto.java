package models;

public class Auto {
    private int id;
    private String model;
    private String color;
    private User user;
    
    public Auto(String model, String color, User user) {
        this.model = model;
        this.color = color;
        this.user = user;
        this.id = -1;
    }
    
    public void printAutoData(){
        System.out.println("#" + this.getId() + " " + this.getModel() + " " + this.getColor() + " " + this.user.getName());
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}
