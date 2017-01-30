package genome.dao.hibernate;

import genome.dao.UserDao;
import genome.models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    
    // language = HQL
    private static final String HQL_GET_ALL_USERS = "FROM User";
    private static final String HQL_GET_USER_BY_ID = "FROM User WHERE id=:id";
    private static final String HQL_GET_USER_BY_LOGIN = "FROM User WHERE login=:login";
    private static final String HQL_GET_USER_BY_TOKEN = "FROM User WHERE token=:token";
    
    // language = SQL
    private static final String SQL_UPDATE_TOKEN = "UPDATE users SET token=:token WHERE id=:id";
    private static final String SQL_SAVE_USER = "INSERT INTO users(" +
            "login, password, name, surname) VALUES (?, ?, ?, ?)";
    private static final String SQL_TOKEN_IS_EXIST = "SELECT EXISTS (SELECT 1 FROM users WHERE token=:token)";
    
    private SessionFactory sessionFactory;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate template;
    
    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory, DataSource dataSource) {
        this.sessionFactory = sessionFactory;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.template = new JdbcTemplate(dataSource);
    }
    
    public List<User> findAll() {
        Session session = getSession();
        
        session.beginTransaction();
        List<User> users = session.createQuery(HQL_GET_ALL_USERS, User.class).getResultList();
        session.getTransaction().commit();
        
        return users;
    }
    
    public User findById(Long id) {
        Session session = getSession();
    
        session.beginTransaction();
        User user = session.createQuery(HQL_GET_USER_BY_ID, User.class).setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
    
        return user;
    }
    
    public User findByLogin(String login) {
        Session session = getSession();
        
        session.beginTransaction();
        User user = session.createQuery(HQL_GET_USER_BY_LOGIN, User.class).setParameter("login", login).getSingleResult();
        session.getTransaction().commit();
        
        return user;
    }
    
    public User findByToken(String token) {
        Session session = getSession();
        
        session.beginTransaction();
        User user = session.createQuery(HQL_GET_USER_BY_TOKEN, User.class).setParameter("token", token).getSingleResult();
        session.getTransaction().commit();
        
        return user;
    }
    
    public User save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        template.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            
            return statement;
        }, keyHolder);
        //System.out.println(keyHolder.getKeyList().get(0).get("id").getClass().getName());
        
        Long id = (Long)keyHolder.getKeyList().get(0).get("id");
        user.setId(id);
        
        return user;
    }
    
    public boolean delete(Long id) {
        return false;
    }
    
    public boolean update(User user) {
        return false;
    }
    
    @Override
    public void updateToken(Long id, String token) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("token", token);
        template.update(SQL_UPDATE_TOKEN, params);
    }
    
    @Override
    public boolean isExistToken(String token) {
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        
        return namedParameterJdbcTemplate.queryForObject(SQL_TOKEN_IS_EXIST, params, Boolean.class);
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
