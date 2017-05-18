package app.service;


import app.exceptions.UserAlreadyExistsException;
import app.exceptions.WrongUserCredentialsException;
import app.model.users.User;

public interface LoginRegisterService {

    User login(String login, String pass) throws WrongUserCredentialsException;

}
