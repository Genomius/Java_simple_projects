package genome.services;

import genome.dao.ChatDao;
import genome.models.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{
    
    @Autowired
    private ChatDao chatDao;
    
    @Override
    public List<Chat> findAll() {
        return chatDao.findAll();
    }
    
    @Override
    public Chat findById(Long id) {
        return chatDao.findById(id);
    }
    
    @Override
    public Chat save(Chat chat) {
        return chatDao.save(chat);
    }
}
