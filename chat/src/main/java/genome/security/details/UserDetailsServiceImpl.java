package genome.security.details;

import genome.dao.UserDao;
import genome.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserDao userDao;
    
    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        if (userDao.isExistToken(token)){
            User user = userDao.findByToken(token);
            List<GrantedAuthority> authorities = createGrantedAuthorities();
            
            return new UserDetailsImpl(user.getLogin(), user.getPassword(), authorities);
        } else {
            return null;
        }
    }
    
    public static List<GrantedAuthority> createGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        
        return authorities;
    }
}
