package genome.dao.hibernate;

import genome.dao.AutoDao;
import genome.dao.UserDao;
import genome.models.Auto;
import genome.models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AutoDaoHibernateImpl implements AutoDao {
    // language = HQL
    private static final String SQL_GET_AUTOS = "from Auto";
    private static final String SQL_GET_AUTO_BY_ID = "from Auto where id = :autoId";
    
    // language = SQL
    private static final String SQL_SAVE_AUTO = "INSERT INTO autos(model, color, user_id) VALUES (:model, :color, :userId)";
    private static final String SQL_DELETE_AUTO = "DELETE FROM autos WHERE id=:id";
    private static final String SQL_UPDATE_AUTO = "UPDATE autos SET model=:model, color=:color, user_id=:userId WHERE id=:id";
    
    private SessionFactory sessionFactory;
    private NamedParameterJdbcTemplate template;
    
    @Autowired
    public AutoDaoHibernateImpl(SessionFactory sessionFactory, DataSource dataSource) {
        this.sessionFactory = sessionFactory;
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<Auto> findAll() {
        Session session = getSession();
        
        session.beginTransaction();
        List<Auto> autos = session.createQuery(SQL_GET_AUTOS, Auto.class).list();
        session.getTransaction().commit();

        return autos;
    }
    
    public Auto find(int id) {
        Session session = getSession();
        
        session.beginTransaction();
        Auto auto = session.createQuery(SQL_GET_AUTO_BY_ID, Auto.class).getSingleResult();
        session.getTransaction().commit();
        
        return auto;
    }
    
    public int save(Auto auto) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("model", auto.getModel());
        paramsMap.put("color", auto.getColor());
        paramsMap.put("user_id", auto.getUser().getId());
        
        template.update(SQL_SAVE_AUTO, paramsMap);
        //TODO: return id
        return 1;
    }
    
    public int update(int id, Auto auto) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("model", auto.getModel());
        paramsMap.put("color", auto.getColor());
        paramsMap.put("user_id", auto.getUser().getId());
        
        template.update(SQL_UPDATE_AUTO, paramsMap);
        
        return id;
    }
    
    public boolean delete(int id) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("id", id);
        
        template.update(SQL_DELETE_AUTO, paramsMap);
        
        return true;
    }
    
    
    private Session getSession() {
        Session session;
        
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        
        return session;
    }
}
