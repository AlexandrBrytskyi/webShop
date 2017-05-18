package app.service;

import app.exceptions.UserAlreadyExistsException;
import app.exceptions.WrongUserCredentialsException;
import app.model.items.Basket;
import app.model.users.Customer;
import app.model.users.User;
import app.repository.BasketPepo;
import app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class LoginRegisterServiceImpl implements LoginRegisterService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override


    public User login(String login, String pass) throws WrongUserCredentialsException {
        User founded = userRepo.findOne(login);
        if (null == founded || !passwordEncoder.matches(pass, founded.getPassword()))
            throw new WrongUserCredentialsException("Wrong login");
        return founded;
    }


}
