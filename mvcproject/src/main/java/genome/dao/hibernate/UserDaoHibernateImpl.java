package genome.dao.hibernate;

import genome.dao.UserDao;
import genome.models.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoHibernateImpl implements UserDao {
    // language = HQL
    private static final String SQL_GET_USERS = "from User ";
    private static final String SQL_GET_USER_BY_ID = "from User where id = :userId";
    
    // language = SQL
    private static final String SQL_SAVE_USER = "INSERT INTO users(name, age) VALUES (:name, :age)";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id=:id";
    private static final String SQL_UPDATE_USER = "UPDATE users SET name=:name, age=:age WHERE id=:id";
    
    private SessionFactory sessionFactory;
    private NamedParameterJdbcTemplate template;
    
    @Autowired
    public UserDaoHibernateImpl(SessionFactory sessionFactory, DataSource dataSource) {
        this.sessionFactory = sessionFactory;
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<User> findAll() {
        Session session = getSession();
        
        session.beginTransaction();
        List<User> users = session.createQuery(SQL_GET_USERS, User.class).list();
        session.getTransaction().commit();

        return users;
    }
    
    public User find(int id) {
        Session session = getSession();
        session.beginTransaction();
        User user = session.createQuery(SQL_GET_USER_BY_ID, User.class).setParameter("userId", id).getSingleResult();
        session.getTransaction().commit();
        
        return user;
    }
    
    public int save(User user) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("name", user.getName());
        paramsMap.put("age", user.getAge());
        
        template.update(SQL_SAVE_USER, paramsMap);
        //TODO: return id
        return 1;
    }
    
    public int update(int id, User user) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("name", user.getName());
        paramsMap.put("age", user.getAge());
        paramsMap.put("id", user.getId());
    
        template.update(SQL_UPDATE_USER, paramsMap);
    
        return id;
    }
    
    public boolean delete(int id) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("id", id);
    
        template.update(SQL_DELETE_USER, paramsMap);
    
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
