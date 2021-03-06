package genome.dao;

import genome.models.Message;

import java.util.List;

public interface MessageDao {
    List<Message> findAll();
    Message findById(Long id);
    Message save(Message message);
    boolean delete(Long id);
    boolean update(Message message);
    
    List<Message> findAllByChatId(Long chatId);
}
