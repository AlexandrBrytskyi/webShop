package app.mvc_controllers.admin;


import app.model.items.Purchase;
import app.model.items.PurchaseState;
import app.model.users.Admin;
import app.model.users.Customer;
import app.model.users.User;
import app.repository.CategoryRepo;
import app.repository.ItemRepository;
import app.repository.PurchaseRepo;
import app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping(value = "/shop/admin/")
public class ShopAdminController {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private ItemRepository itemRepository;


    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String openMainPage(HttpServletRequest request,
                               HttpServletResponse response,
                               Model model,
                               @ModelAttribute(name = "user") User user) {

        User fromCoockies = getUserFromCookies(request);
        if (fromCoockies != null) user = fromCoockies;
        if (null == user || user.getLogin() == null) return "login";
        model.addAttribute("user", user);
        addUserLoginToCookies(response, user.getLogin());
        return "adminMain";
    }


    private void addUserLoginToCookies(HttpServletResponse response, String login) {
        response.addCookie(new Cookie("userLogin", login));
    }


    private User getUserFromCookies(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("userLogin")) return userRepo.findOne(cookie.getValue());
        }
        return null;
    }


    @RequestMapping(value = "purchase/cancel/{purchId}", method = RequestMethod.POST)
    public String cancelPurchase(@PathVariable(value = "purchId") int purchId,
                                 Model model,
                                 HttpServletRequest request) {

        Admin admin = (Admin) getUserFromRequest(request);

        Purchase one = purchaseRepo.findOne(purchId);
        one.setDateChanged(new Date());
        one.setState(PurchaseState.CANCELLED);
        purchaseRepo.save(one);

        return "redirect:../../../purchase";

    }

    @RequestMapping(value = "purchase/submit/{purchId}", method = RequestMethod.POST)
    public String submitPurchase(@PathVariable(value = "purchId") int purchId,
                                 Model model,
                                 HttpServletRequest request) {

        Admin admin = (Admin) getUserFromRequest(request);

        Purchase one = purchaseRepo.findOne(purchId);
        one.setDateChanged(new Date());
        one.setState(PurchaseState.SUBMITTED);
        one.setSeller(admin);
        one.getItemBought().setAmountAvailable(one.getItemBought().getAmountAvailable() - one.getItemsAmount());
        itemRepository.save(one.getItemBought());
        purchaseRepo.save(one);

        return "redirect:../../../purchase";

    }

    private app.model.users.User getUserFromRequest(HttpServletRequest request) {
        try {
            return userRepo.findOne(request.getUserPrincipal().getName());
        } catch (Exception e) {
            return null;
        }
    }


}
