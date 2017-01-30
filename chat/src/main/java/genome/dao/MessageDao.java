package genome.dao;

import genome.models.Message;

import java.util.List;

public interface MessageDao {
    List<Message> findAll();
    Message find(Integer id);
    Integer save(Message message);
    boolean delete(Integer id);
    boolean update(Message message);
}
