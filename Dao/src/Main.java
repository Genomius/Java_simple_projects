public class Main {
    public static void main(String[] args) {
        UsersService usersService = new UsersService(new UsersDaoFakeImpl());
    
        System.out.println(usersService.isRegistered("Denis"));
        System.out.println(usersService.isRegistered("Ben"));
        System.out.println(usersService.isRegistered("Nikolay"));
        System.out.println(usersService.isRegistered("Ingiborg"));
        System.out.println(usersService.isRegistered("Valeriy"));
        System.out.println(usersService.isRegistered("Ilya"));
    }
}
