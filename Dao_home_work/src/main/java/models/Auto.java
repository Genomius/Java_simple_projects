package models;

public class Auto {
    private int id;
    private String model;
    private String color;
    private int user_id;
    
    public Auto(String model, String color, int user_id) {
        this.model = model;
        this.color = color;
        this.user_id = user_id;
        this.id = -1;
    }
    
    public void printAutoData(){
        System.out.println("#" + this.getId() + " " + this.getModel() + " " + this.getColor() + " " + this.getUserId());
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
    
    public int getUserId() {
        return user_id;
    }
    
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
