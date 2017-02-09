package genome.services;

import genome.dao.MessageDao;
import genome.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;
    
    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }
    
    public List<Message> findAllByChatId(Long chatId) {
        return messageDao.findAllByChatId(chatId);
    }
    
    @Override
    public Message findById(Long id) {
        return null;
    }
    
    @Override
    public Message save(Message message) {
        return messageDao.save(message);
    }
}
