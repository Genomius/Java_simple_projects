package genome.dto;

public class MessageDto {
    private Long id;
    private String login;
    private String content;
    
    public Long getId() {
        return id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getContent() {
        return content;
    }
    
    public MessageDto(Long id, String login, String content) {
        this.id = id;
        this.login = login;
        this.content = content;
    }
}
