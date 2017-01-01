public class UsersDaoFakeImpl implements UsersDao{
    @Override
    public User[] findAll(){
        User den = new User("Denis", 24, "Govnocoder");
        User ilya = new User("Ilya", 22, "Raspizdyaj");
        User kolya = new User("Nikolay", 25, "Gamer");
        
        return new User[]{den, ilya, kolya};
    }
}
