package genome.controllers;

import genome.models.Chat;
import genome.models.Message;
import genome.models.User;
import genome.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
//    @Autowired
//    private SimpMessagingTemplate template;
//
//    @MessageMapping(value = "/chat")
//    public void getMessage(Message message, User user, Chat chat) {
//        System.out.println("Get message " + message.getContent() +  " from " + message.getSender());
//        template.convertAndSend("/topic/messages", new Message(user, chat, message.getContent()));
//    }
    
    @Autowired
    private ChatService chatService;
    
    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public ResponseEntity<List<Chat>> getChats(){
        return new ResponseEntity<>(chatService.findAll(), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/chats", method = RequestMethod.POST)
    public ResponseEntity<Chat> addChat(
            @RequestBody Chat chat
    ){
        return new ResponseEntity<>(chatService.save(chat), HttpStatus.CREATED);
    }
}
