public class Main {
    public static void main(String[] args) {
        int value = Singleton.getInstance().getField();
        System.out.println("field: " + value);

        value = Singleton.getInstance().getField();
        System.out.println("field: " + value);
    }
}
