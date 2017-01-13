import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Auto vw = new Auto("o536mk55", "VW Golf III");
        Auto bmw = new Auto("t185tk54", "BMW 6-series");
    
        User denis = new User("Denis", vw);
        User dima = new User("Dima", vw);
        User oleg = new User("Oleg", vw);
        User sergey = new User("Sergey", bmw);
        User nikolay = new User("Nikolay", bmw);

        
        
        denis.start();
        dima.start();
        oleg.start();
        sergey.start();
        nikolay.start();
    }
}
