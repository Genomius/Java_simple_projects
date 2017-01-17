package dao.hibernate;

import dao.UserDao;
import models.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    // language = HQL
    private static final String SQL_GET_USERS = "from User";
    private static final String SQL_GET_USER_BY_ID = "from User where id = :userId";
    
    private Session session;
    
    public UserDaoHibernateImpl(Session session) {
        this.session = session;
    }
    
    public List<User> findAll() {
        session.beginTransaction();
        List<User> users = session.createQuery(SQL_GET_USERS, User.class).list();
        session.getTransaction().commit();

        return users;
    }
    
    public User find(int id) {
        session.beginTransaction();
        User user = session.createQuery(SQL_GET_USER_BY_ID, User.class).getSingleResult();
        session.getTransaction().commit();
        
        return user;
    }
    
    public int save(User entity) {
        return 0;
    }
    
    public int update(int id, User entity) {
        return 0;
    }
    
    public boolean delete(int id) {
        return false;
    }
}
