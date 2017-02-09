package genome.dao.hibernate;

import genome.dao.ChatDao;
import genome.models.Chat;
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
import java.util.List;

@Repository
public class ChatDaoImpl implements ChatDao {
    
    // language = HQL
    private static final String HQL_GET_ALL_CHATS = "FROM Chat";
    private static final String HQL_GET_CHAT_BY_ID = "FROM Chat WHERE id=:id";
    
    
    // language = SQL
    private static final String SQL_SAVE_CHAT = "INSERT INTO chats(title) VALUES (?)";
    
    private SessionFactory sessionFactory;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate template;
    
    @Autowired
    public ChatDaoImpl(SessionFactory sessionFactory, DataSource dataSource) {
        this.sessionFactory = sessionFactory;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.template = new JdbcTemplate(dataSource);
    }
    
    @Override
    public List<Chat> findAll() {
        Session session = getSession();
    
        session.beginTransaction();
        List<Chat> chats = session.createQuery(HQL_GET_ALL_CHATS, Chat.class).getResultList();
        session.getTransaction().commit();
    
        return chats;
    }

    @Override
    public Chat findById(Long id) {
        Session session = getSession();
    
        session.beginTransaction();
        Chat chat = session.createQuery(HQL_GET_CHAT_BY_ID, Chat.class).setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
    
        return chat;
    }
    
    @Override
    public Chat save(Chat chat) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        template.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_CHAT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, chat.getTitle());
        
            return statement;
        }, keyHolder);
    
        Long id = (Long)keyHolder.getKeyList().get(0).get("id");
        chat.setId(id);
    
        return chat;
    }
    
    @Override
    public boolean delete(Integer id) {
        return false;
    }
    
    @Override
    public boolean update(Chat chat) {
        return false;
    }
    
    @Override
    public boolean addUserToChat(Integer userId, Integer chatId) {
        return false;
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
