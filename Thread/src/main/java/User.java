import java.util.Random;

public class User extends Thread {
    private String name;
    private Auto requestedAuto;
    private Auto currentAuto;
    
    public User(String name, Auto auto) {
        this.name = name;
        currentAuto = null;
        requestedAuto = auto;
    }

    public void driving(Integer driverTimeNeeded) throws InterruptedException {
        sleep(100);
        synchronized (requestedAuto){
            
            while(driverTimeNeeded > 0){
                sleep(1000);
                System.out.println(this.name + " на автомобиле: " + requestedAuto.getModel() + " осталось ехать: " + driverTimeNeeded + "c.");
                driverTimeNeeded--;
            }
            
            System.out.println(this.name + " приехал");
        }
    }
    
    public void run(){
        for(int i=0;i<3;i++)
            try {
                driving(new Random().nextInt(10) + 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
