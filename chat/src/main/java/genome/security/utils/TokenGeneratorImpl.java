package genome.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class TokenGeneratorImpl implements TokenGenerator {
    private static int LENGTH = 20;
    private static int OFFSET = 6;
    private static final char[] CHARS =
            {'a', 'b', 'c',	'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
            };
    
    private PasswordEncoder passwordEncoder;
    private Random random;
    private Date date;
    
    public TokenGeneratorImpl() {
        passwordEncoder = new BCryptPasswordEncoder();
        this.date = new Date();
        this.random = new Random();
    }
    
    @Override
    public String generateToken() {
        char[] buffer = genSalt().toCharArray();
        for(int i=0; i< buffer.length; i++)
            if(!isLegal(buffer[i]))
                buffer[i] = CHARS[Math.abs(random.nextInt()) & CHARS.length];
        
        return new String(buffer, OFFSET, LENGTH);
    }
    
    private boolean isLegal(char c) {
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }
    
    private String genSalt() {
        return passwordEncoder.encode(Long.toString(random.nextLong() ^ date.getTime()));
    }
}
