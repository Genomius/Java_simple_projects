import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Newspaper {
    public static void main(String[] args) {
        NewspaperMaker maker = new NewspaperMaker();
        maker.addObserver(new ObserversOfMoscow());
        maker.addObserver(new ObserversOfKazan());
        maker.createNewsPaper(new String[]{"Шла Саша по шоссе и сосала сушку", "Шёл Вася в универ..."});
        maker.createNewsPaper(new String[]{"В Омске случилось наводнение", "В Казани ничего не произошло", "Больше новостей нет..."});
    }
}

interface Observed{
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class NewspaperMaker implements Observed{
    private String[] news;
    private List<Observer> observers = new ArrayList<>();

    public void createNewsPaper(String[] news){
        this.news = news;
        notifyObservers();
    }

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
        observers.remove(o);
    }

    public void notifyObservers(){
        for(Observer o: observers){
            o.handleEvent(news);
        }
    }
}

interface Observer{
    void handleEvent(String[] news);
}

class ObserversOfMoscow implements Observer{
    public void handleEvent(String[] news){
        String latest_news = "";
        for(String n: news){
            latest_news += ("\t - " + n + '\n');
        }
        System.out.println("Newspaper ready for Moscow, guys! \n Latest news: \n" + latest_news);
    }
}

class ObserversOfKazan implements Observer{
    public void handleEvent(String[] news){
        String latest_news = "";
        for(String n: news){
            latest_news += ("\t - " + n + '\n');
        }
        System.out.println("Newspaper ready for Kazan, guys! \n Latest news: \n" + latest_news);
    }
}
