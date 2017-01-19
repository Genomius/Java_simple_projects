package genome.dao.hibernate;

import genome.dao.AutoDao;
import genome.dao.UserDao;
import genome.models.Auto;
import genome.models.User;
import org.hibernate.Session;

import java.util.List;

public class AutoDaoHibernateImpl implements AutoDao {
    // language = HQL
    private static final String SQL_GET_AUTOS = "from Auto";
    private static final String SQL_GET_AUTO_BY_ID = "from Auto where id = :autoId";
    
    private Session session;
    
    public AutoDaoHibernateImpl(Session session) {
        this.session = session;
    }
    
    public List<Auto> findAll() {
        session.beginTransaction();
        List<Auto> autos = session.createQuery(SQL_GET_AUTOS, Auto.class).list();
        session.getTransaction().commit();

        return autos;
    }
    
    public Auto find(int id) {
        session.beginTransaction();
        Auto auto = session.createQuery(SQL_GET_AUTO_BY_ID, Auto.class).getSingleResult();
        session.getTransaction().commit();
        
        return auto;
    }
    
    public int save(Auto entity) {
        return 0;
    }
    
    public int update(int id, Auto entity) {
        return 0;
    }
    
    public boolean delete(int id) {
        return false;
    }
}
