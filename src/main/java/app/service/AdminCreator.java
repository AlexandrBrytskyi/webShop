package app.service;

import app.model.users.Admin;
import app.model.users.User;
import app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminCreator {

    @Autowired(required = true)
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void insertAdminIfNeed(){
        if (userRepo.findOne("admin")==null) {
            userRepo.save(new Admin("admin", passwordEncoder.encode("admin")));
        }
    }

}
