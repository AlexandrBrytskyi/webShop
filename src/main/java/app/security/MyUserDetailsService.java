package app.security;

import app.model.users.Admin;
import app.model.users.User;
import app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service()
public class MyUserDetailsService implements UserDetailsService {

    //get user from the database, via Hibernate
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User fromDao = userRepo.findOne(login);
        if (fromDao == null) throw new UsernameNotFoundException("Invalid login " + login);

        System.out.println("from dao user pass " + fromDao.getPassword());
        System.out.println("in user details service");

        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                fromDao.getLogin(),
                fromDao.getPassword(),
                true, true, true, true, new ArrayList<GrantedAuthority>() {{
            if (fromDao instanceof Admin) {
                add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                System.out.println("User has admin permissions");
            } else {
                add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            System.out.println("User have user permissins");
        }}
        );


        return user;
    }


}