package genome.dao;

import genome.models.Chat;

import java.util.List;

public interface ChatDao {
    List<Chat> findAll();
    Chat findById(Long id);
    Chat save(Chat chat);
    boolean delete(Integer id);
    boolean update(Chat chat);
    boolean addUserToChat(Integer userId, Integer chatId);
}
