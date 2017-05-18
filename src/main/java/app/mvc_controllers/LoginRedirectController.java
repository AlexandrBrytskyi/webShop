package app.mvc_controllers;

import app.exceptions.UserAlreadyExistsException;
import app.exceptions.WrongUserCredentialsException;
import app.model.users.Admin;
import app.model.users.User;
import app.service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class LoginRedirectController {

    @Autowired
    private LoginRegisterService loginService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/shop/itemsMainMenu?showItems=true";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String redirectLogin() {
        return "loginPage";
    }


    @RequestMapping(value = "logined", method = RequestMethod.POST)
    public String login(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String pass,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            User logged = loginService.login(login, pass);
            redirectAttributes.addFlashAttribute("user", logged);
            System.out.println("returning redirect");
            return (logged instanceof Admin) ? "redirect:shop/admin/main" : "redirect:shop/itemsMainMenu?showItems=true";
        } catch (WrongUserCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            System.out.println("returninh on login");
            return "loginPage";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@ModelAttribute(name = "message") String errorMessage,
                        Model model) {
        model.addAttribute("message", errorMessage);
        return "loginPage";
    }

}
