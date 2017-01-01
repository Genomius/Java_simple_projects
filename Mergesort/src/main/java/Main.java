public class Main {
    public static void main(String[] args) {
        List list = new List();
        
//        list.insert(8);
//        list.insert(1);
//        list.insert(3);
//        list.insert(4);
//        list.insert(0);
//        list.insert(9);
//        list.insert(2);
//        list.insert(5);
//        list.insert(6);
//        list.insert(7);
        
        list.insert(6);
        list.insert(5);
        list.insert(5);
        list.insert(3);
        list.insert(12);
        list.insert(3);
        list.insert(1);
        list.insert(50);
        list.insert(8);
        list.insert(7);
        list.insert(2);
        list.insert(4);
        list.insert(25);
        list.insert(0);
        list.insert(4);
        list.insert(-1);
        list.insert(4);
        list.insert(-5);
        list.insert(-20);
        list.insert(-451);
        
        list.insert(6);
        list.insert(5);
        list.insert(5);
        list.insert(3);
        list.insert(12);
        list.insert(3);
        list.insert(1);
        list.insert(50);
        list.insert(8);
        list.insert(7);
        list.insert(2);
        list.insert(4);
        list.insert(25);
        list.insert(0);
        list.insert(4);
        list.insert(-1);
        list.insert(4);
        list.insert(-5);
        list.insert(-20);
        list.insert(-451);
        list.insert(2);
        list.insert(25);
        list.insert(4);
        list.insert(0);
        list.insert(4);
        list.insert(-1);
        list.insert(4);
        list.insert(-5);
        list.insert(-20);
        list.insert(-451);
        
        List list2 = new List();
    
        list2.insert(6);
        list2.insert(5);
        list2.insert(5);
        list2.insert(3);
        list2.insert(12);
        list2.insert(3);
        list2.insert(1);
        list2.insert(50);
        list2.insert(8);
        list2.insert(7);
        list2.insert(2);
        list2.insert(4);
        list2.insert(25);
        list2.insert(0);
        list2.insert(4);
        list2.insert(-1);
        list2.insert(4);
        list2.insert(-5);
        list2.insert(-20);
        list2.insert(-451);
    
        list2.insert(6);
        list2.insert(5);
        list2.insert(5);
        list2.insert(3);
        list2.insert(12);
        list2.insert(3);
        list2.insert(1);
        list2.insert(50);
        list2.insert(8);
        list2.insert(7);
        list2.insert(2);
        list2.insert(4);
        list2.insert(25);
        list2.insert(0);
        list2.insert(4);
        list2.insert(-1);
        list2.insert(4);
        list2.insert(-5);
        list2.insert(-20);
        list2.insert(-451);
        list2.insert(2);
        list2.insert(4);
        list2.insert(25);
        list2.insert(0);
        list2.insert(4);
        list2.insert(-1);
        list2.insert(4);
        list2.insert(-5);
        list2.insert(-20);
        list2.insert(-451);
        
    
        // Node node = list.find(0);
        // System.out.println(node.getData());
    
        // System.out.println("Count before delete: " + list.length());
        // list.delete(1);
        // System.out.println("Count after  delete: " + list.length());
    
        long startTime2 = System.nanoTime();
        
        list2.primitive_sort();
    
        long time2 = System.nanoTime() - startTime2;
        double seconds2 = (double)time2 / 1000000000.0;
    
    
        long startTime1 = System.nanoTime();
    
        list.sort();
    
        long time1 = System.nanoTime() - startTime1;
        double seconds1 = (double)time1 / 1000000000.0;
        
        System.out.println("Время выполнения в наносекундах в сортировке слияния, при N=" + list.length() + ": " + seconds1);
        System.out.println("Время выполнения в наносекундах в пузырьковой сортировке, при N=" + list2.length() + ": " + seconds2);
    
    }
}
