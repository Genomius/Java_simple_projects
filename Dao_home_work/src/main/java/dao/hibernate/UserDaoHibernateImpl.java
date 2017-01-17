package dao.hibernate;

import dao.UserDao;
import models.User;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public static final String SQL_GET_USERS = "SELECT * FROM UserDao";
    public static final String SQL_GET_USER_BY_ID = "SELECT * FROM UserDao WHERE id = :userId";
    
    Session session;
    
    public UserDaoHibernateImpl(Session session) {
        this.session = session;
    }
    
    @Override
    public List<User> findAll() {
        session.beginTransaction(); // Транзакция начинается, запрещаем другим потокам доступ к таблице
        
        List<User> users = session.createQuery(SQL_GET_USERS, User.class).list();
        session.getTransaction().commit(); // Транзакция закончилась, разрешаем доступ
        
        return users;
    }
    
    @Override
    public User find(int id) {
        session.beginTransaction();
        
        User user = session.createQuery(SQL_GET_USER_BY_ID, User.class).setParameter("userId", id).getSingleResult();
        session.getTransaction().commit();
        
        return user;
    }
    
    @Override
    public int save(User user) {
        return 0;
    }
    
    @Override
    public int update(int userId, User newUser) {
        return 0;
    }
    
    @Override
    public boolean delete(int id) {
        return false;
    }
}
