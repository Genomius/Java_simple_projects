package genome.services;

import genome.models.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> findAll();
    Chat findById(Long id);
    Chat save(Chat chat);
}
