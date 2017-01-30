package genome.security.provider;

import genome.config.SecurityConfig;
import genome.security.auth.TokenAuthentification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class TokenAuthentificationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentification tokenAuthentification = (TokenAuthentification)authentication;
        String token = (String)tokenAuthentification.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(token);
        
        if(userDetails == null)
            throw new IllegalArgumentException("User not found");
        
        tokenAuthentification.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return authentication;
    }
    
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(TokenAuthentification.class);
    }
}
