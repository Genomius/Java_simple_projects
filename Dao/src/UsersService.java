public class UsersService {
    private UsersDao usersDao;
    
    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }
    
    public boolean isRegistered(String userName){
        User[] users = usersDao.findAll();
        
        for(User user : users){
            if(user.getName().equals(userName))
                return true;
        }
        return false;
    }
}
