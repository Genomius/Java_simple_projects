public class Auto {
    private String id;
    private String model;
    private User currentUser;
    
    public String getModel() {
        return model;
    }
    
    public Auto(String id, String model) {
        this.id = id;
        this.model = model;
        this.currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
}
