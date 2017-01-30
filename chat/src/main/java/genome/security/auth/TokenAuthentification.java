package genome.security.auth;

import genome.security.details.UserDetailsServiceImpl;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class TokenAuthentification extends AbstractAuthenticationToken {
    public final String token;
    
    public TokenAuthentification(String token) {
        super(UserDetailsServiceImpl.createGrantedAuthorities());
        this.token = token;
    }
    
    public TokenAuthentification(Collection<? extends GrantedAuthority> authorities, String token) {
        super(authorities);
        this.token = token;
    }
    
    @Override
    public Object getCredentials() {
        return this.getAuthorities();
    }
    
    @Override
    public Object getPrincipal() {
        return token;
    }
}
