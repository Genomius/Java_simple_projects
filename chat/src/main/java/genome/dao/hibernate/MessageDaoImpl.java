package genome.dao.hibernate;

import genome.dao.MessageDao;
import genome.models.Message;
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
public class MessageDaoImpl implements MessageDao {
    
    // language = HQL
    private static final String HQL_GET_ALL_MESSAGES = "FROM Message";
    private static final String HQL_GET_MESSAGE_BY_CHAT_ID = "FROM Message m WHERE m.chat.id=:chat_id";
    
    // language = SQL
    private static final String SQL_SAVE_MESSAGE = "INSERT INTO messages(user_id, chat_id, content) VALUES (?,?,?)";
    
    private SessionFactory sessionFactory;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate template;
    
    @Autowired
    public MessageDaoImpl(SessionFactory sessionFactory, DataSource dataSource) {
        this.sessionFactory = sessionFactory;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.template = new JdbcTemplate(dataSource);
    }
    
    @Override
    public List<Message> findAll() {
        Session session = getSession();
    
        session.beginTransaction();
        List<Message> messages = session.createQuery(HQL_GET_ALL_MESSAGES, Message.class).getResultList();
        session.getTransaction().commit();
    
        return messages;
    }
    
    @Override
    public Message findById(Long id) {
        return null;
    }
    
    @Override
    public Message save(Message message) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        template.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_MESSAGE, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, message.getSender().getId());
            statement.setLong(2,message.getChat().getId());
            statement.setString(3, message.getContent());
        
            return statement;
        }, keyHolder);
    
        Long id = (Long)keyHolder.getKeyList().get(0).get("id");
        message.setId(id);
    
        return message;
    }
    
    public List<Message> findAllByChatId(Long chatId){
        Session session = getSession();
    
        session.beginTransaction();
        List<Message> messages = session.createQuery(HQL_GET_MESSAGE_BY_CHAT_ID, Message.class).setParameter("chat_id", chatId).getResultList();
        session.getTransaction().commit();
    
        return messages;
    }
    
    
    @Override
    public boolean delete(Long id) {
        return false;
    }
    
    @Override
    public boolean update(Message message) {
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
