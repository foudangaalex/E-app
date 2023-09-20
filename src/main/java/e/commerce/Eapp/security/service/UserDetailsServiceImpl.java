package e.commerce.Eapp.security.service;

import e.commerce.Eapp.security.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= accountService.loadUserByUserName(username);
        if(appUser==null) throw new UsernameNotFoundException(String.format("User %s not found"+username));
        String[] roles=appUser.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new);

        UserDetails userDetails= User
                .withUsername(username)
                .password(appUser.getPassword())
                .roles(roles)
                .build();
        return userDetails;
    }
}
