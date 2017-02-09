package genome.controllers;

import genome.dto.MessageDto;
import genome.models.Chat;
import genome.models.Message;
import genome.models.User;
import genome.services.ChatService;
import genome.services.MessageService;
import genome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    
    @Autowired
    SimpMessagingTemplate template;
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ChatService chatService;
    
    @RequestMapping(value = "/messages/{chatId}", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDto>> getMessages(
            @PathVariable("chatId") Long chatId
    ){
        //ToDO: позор на мою голову (запилить лямбду)
        List<Message> messages = messageService.findAllByChatId(chatId);
        List<MessageDto> messageDtos = new ArrayList<>();
        for (Message message : messages) {
            messageDtos.add(new MessageDto(
                    message.getId(),
                    message.getSender().getLogin(),
                    message.getContent())
            );
        }
            
        return new ResponseEntity<>(messageDtos, HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/messages/{chatId}", method = RequestMethod.POST)
    public ResponseEntity<MessageDto> addMessage(
            @RequestHeader("Auth-Token") String token,
            @PathVariable("chatId") Long chatId,
            @RequestBody String content
    ){
        Message message = messageService.save(
                        new Message(userService.findByToken(token), chatService.findById(chatId), content));
        MessageDto messageDto = new MessageDto(message.getId(), message.getSender().getLogin(), message.getContent());
    
        template.convertAndSend("/topic/" + chatId.toString() + "/messages", messageDto);
    
        return new ResponseEntity<>(messageDto, HttpStatus.CREATED);
    }
}
