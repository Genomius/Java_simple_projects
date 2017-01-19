package genome.fabrics;

import genome.dao.AutoDao;
import genome.dao.hibernate.AutoDaoHibernateImpl;
import org.hibernate.Session;

public class AutoFactory {
    private static AutoFactory instance;
    private AutoDao autoDao;
    
    public AutoFactory() {
        Session session = HibernateFabric.getInstance().getSession();
        
        autoDao = (AutoDao) new AutoDaoHibernateImpl(session);
    }
    
    static {
        instance = new AutoFactory();
    }
    
    public static AutoFactory getInstance(){
        return instance;
    }
    
    public AutoDao getAutoDao(){
        return autoDao;
    }
}
