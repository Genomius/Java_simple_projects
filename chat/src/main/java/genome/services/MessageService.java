package genome.services;

import genome.models.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();
    Message findById(Long id);
    Message save(Message message);
    
    List<Message> findAllByChatId(Long chatId);
}
