package genome.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
    
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
    
        if (!request.getRequestURI().equals("/auth"))
            filterChain.doFilter(request, response);
            
        Cookie[] cookies = request.getCookies();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username != null && password != null){
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            response.sendRedirect("/");
        }
        else {
            filterChain.doFilter(request, response);
        }
    }
    
    public void destroy() {
        
    }
}
