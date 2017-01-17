package fabrics;

import dao.UserDao;
import dao.hibernate.UserDaoHibernateImpl;
import org.hibernate.Session;

public class UserFactory {
    private static UserFactory instance;
    private UserDao userDao;
    
    public UserFactory() {
        Session session = HibernateFabric.getInstance().getSession();
        
        userDao = (UserDao) new UserDaoHibernateImpl(session);
    }
    
    static {
        instance = new UserFactory();
    }
    
    public static UserFactory getInstance(){
        return instance;
    }
    
    public UserDao getUserDao(){
        return userDao;
    }
}
