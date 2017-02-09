import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.util.*;

class Product{
    String name;
    int price;
    
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
}

class SortByName implements Comparator<Product> {
    
    @Override
    public int compare(Product p1, Product p2) {
        String name1 = p1.getName();
        String name2 = p2.getName();
        
        return name1.compareTo(name2);
    }
}

class SortByPrice implements Comparator<Product> {
    
    @Override
    public int compare(Product p1, Product p2) {
        Integer price1 = p1.getPrice();
        Integer price2 = p2.getPrice();
        
        return price1.compareTo(price2);
    }
}

class CustomArray<T> implements Iterable {
    private T[] array;
    public int length = 0;
    
    public CustomArray(Class<T> t, int size) {
        @SuppressWarnings("unchecked")
        final T[] array = (T[]) Array.newInstance(t, size);
        this.array = array;
    }
    
    public void add(T elem){
        array[length] = elem;
        length++;
    }
    
    @Override
    public Iterator iterator() {
        return new CustomArrayIterator();
    }
    
    class CustomArrayIterator implements Iterator {
        T next = null;
        int i = 0;
        
        @Override
        public boolean hasNext() {
            return i < length;
        }
    
        @Override
        public Object next() {
            if(i < length)
                next = array[i];
            i++;
            
            return next;
        }
    }
}

abstract class Digest {
    private Map<byte[], byte[]> cache = new HashMap<byte[], byte[]>();
    
    public byte[] digest(byte[] input) {
        byte[] result = cache.get(input);
        
        if (result == null) {
            synchronized (cache) {
                result = cache.get(input);
                
                if (result == null) {
                    result = doDigest(input);
                    
                    cache.put(input, result);
                }
            }
        }
        return result;
    }
    
    protected abstract byte[] doDigest(byte[] input);
    
}

class DigestImpl extends Digest {
    
    @Override
    protected byte[] doDigest(byte[] input) {
        return new byte[0];
    }
}

class E {
    E next;
    int val;
}

class CustomLinkedList {
    static E reverse(E e){
        if (e.next.next == null) {
            e.next.next = e;
            e = e.next;
            e.next.next = null;
        }
        else {
            reverse(e.next);

            
            e.next.next = null;
        }
        
        return e;
    }
}



public class Program {
    public static void main(String[] args) {
        Collections.sort
//        Digest digest = new DigestImpl();
//        digest.digest(new byte[0]);
//
//        CustomLinkedList customLinkedList = new CustomLinkedList();
//        E e1 = new E();
//        e1.val = 1;
//        E e2 = new E();
//        e2.val = 2;
//        E e3 = new E();
//        e3.val = 3;
//        E e4 = new E();
//        e4.val = 4;
//
//        e1.next = e2;
//        e2.next = e3;
//        e3.next = e4;
//        e4.next = null;
//
//        E result = CustomLinkedList.reverse(e1);
//
//        do{
//            System.out.print(result.val + " ");
//            result = result.next;
//        }
//        while(result.next != null);
        
    }
    
    
    
    
    
    
    
    private void iteratorAndIterable(){
        CustomArray<String> customArray = new CustomArray<>(String.class, 5);
        customArray.add("1");
        customArray.add("2");
        customArray.add("3");
        customArray.add("4");
        customArray.add("5");
    
        Iterator iterator = customArray.iterator();
    
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }
    
    private void sortingWithComparator(){
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("broad", 30));
        products.add(new Product("milk", 45));
        products.add(new Product("eggs", 50));
        products.add(new Product("apple", 10));
    
        for (Product product : products) {
            System.out.print(product.name + " ");
        }
        System.out.println();
    
        products.sort(new SortByName());
    
        for (Product product : products) {
            System.out.print(product.name + " ");
        }
        System.out.println();
        
        products.sort(new SortByPrice());
        for (Product product : products) {
            System.out.print(product.price + " ");
        }
        System.out.println();
    }
    
    void simpleSorting(){
        String[] strArray = {"4", "2", "1", "8", "3"};
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add(strArray[0]);
        treeSet.add(strArray[1]);
        treeSet.add(strArray[2]);
        treeSet.add(strArray[3]);
        treeSet.add(strArray[4]);
    
        Iterator<String> i = treeSet.iterator();
        while(i.hasNext())
            System.out.print(i.next() + " ");
        System.out.println();
    
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(0, strArray[0]);
        linkedHashMap.put(1, strArray[1]);
        linkedHashMap.put(2, strArray[2]);
        linkedHashMap.put(3, strArray[3]);
        linkedHashMap.put(4, strArray[4]);
    
        for(int j = 0; j < linkedHashMap.size(); j++)
            System.out.print(linkedHashMap.get(j) +  " ");
        System.out.println();
    
        Arrays.sort(strArray);
        linkedHashMap.clear();
        linkedHashMap.put(0, strArray[0]);
        linkedHashMap.put(1, strArray[1]);
        linkedHashMap.put(2, strArray[2]);
        linkedHashMap.put(3, strArray[3]);
        linkedHashMap.put(4, strArray[4]);
    
        for(int j = 0; j < linkedHashMap.size(); j++)
            System.out.print(linkedHashMap.get(j) +  " ");
        System.out.println();
    }
    
    private void passwordEncoderCheck(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
        String pass = "123321";
        String encodedPass;
    
        encodedPass = passwordEncoder.encode(pass);
        if(passwordEncoder.matches(pass, encodedPass)){
            System.out.println("Done");
        } else throw new IllegalArgumentException("invalid password");
    
        System.out.println(pass);
        System.out.println(encodedPass);
    }
}
