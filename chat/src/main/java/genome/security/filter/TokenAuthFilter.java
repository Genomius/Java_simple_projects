package genome.security.filter;

import genome.security.auth.TokenAuthentification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.filter.GenericFilterBean;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthFilter extends GenericFilterBean {
    private static final String HEADER = "Auth-Token";
    
    private AuthenticationManager authenticationManager;
    
    public TokenAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        
        String headerValue = request.getHeader(HEADER);
        
        try {
            if (isNotRequiringProtection(request))
                filterChain.doFilter(servletRequest, servletResponse);
            else if (headerValue == null || headerValue.equals(""))
                throw new IllegalArgumentException("Token not found");
            else {
                authenticationManager.authenticate(new TokenAuthentification(headerValue));
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        catch (AuthenticationException e){
            throw new IllegalArgumentException(e);
        }
    }
    
    private boolean isNotRequiringProtection(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/users") && request.getMethod().equals("POST") ||
                request.getRequestURI().endsWith("favicon.ico") ||
                request.getRequestURI().startsWith("/login") && request.getMethod().equals("POST");
    }
}
