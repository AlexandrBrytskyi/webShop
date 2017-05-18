package app.mvc_controllers;

import app.model.items.*;
import app.model.users.Customer;
import app.model.users.User;
import app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/shop/")
public class ShopCustomerController {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private BasketPepo basketPepo;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemAmountEntryRepo itemAmountEntryRepo;

    @Autowired
    private CustomerBasketsSessionsContext customerBasketsSessionsContext;


    @RequestMapping(value = "itemsMainMenu", method = RequestMethod.GET)
    public String showItemsMainMenu(Model model,
                                    HttpServletRequest request,
                                    @RequestParam(name = "showItems", required = false, defaultValue = "true") boolean showItems,
                                    RedirectAttributes redirectAttributes) {

        System.out.println("show item" + showItems);
        Basket basket = customerBasketsSessionsContext.getBasket(request);

        Iterable<Categoriya> categories = categoryRepo.findAllByParentIsNull();
        model.addAttribute("categories", categories);
        boolean isAdmin = request.isUserInRole("ADMIN");
        model.addAttribute("isAdmin", isAdmin);
        basket = customerBasketsSessionsContext.getBasket(request);
        model.addAttribute("basket", basket);
        model.addAttribute("showPurch", customerBasketsSessionsContext.isLogined(request));

        if (showItems) {
            List<Item> content = itemRepository.findAll(new PageRequest(0, 1)).getContent();
            Item firstItem;
            if (content.size() == 0) firstItem = null;
            else firstItem = content.get(0);

            if (firstItem == null) {
                model.addAttribute("noItems", true);
            } else {
                List<Item> results = itemRepository.findAll(new PageRequest(0,20)).getContent();
                model.addAttribute("results",results);
            }
        }
        return "mainMenuItemsPage";
    }

    private app.model.users.User getUserFromRequest(HttpServletRequest request) {
        try {
            return userRepo.findOne(request.getUserPrincipal().getName());
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "purchase", method = RequestMethod.GET)
    public String showPurchases(Model model,
                                HttpServletRequest request) {

        User user = getUserFromRequest(request);
        boolean isAdmin = request.isUserInRole("ADMIN");
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", isAdmin);
        Iterable<Purchase> purchases;
        if (isAdmin) purchases = purchaseRepo.findTop20ByStateOrderByDateChanged(PurchaseState.CREATED);
        else
            purchases = purchaseRepo.findTop20ByCustomerBoughtPhoneOrderByDateChangedAsc(customerBasketsSessionsContext.getCustomer(request).getPhone());
        model.addAttribute("purchases", purchases);

        model.addAttribute("basket", customerBasketsSessionsContext.getBasket(request));
        model.addAttribute("showPurch", customerBasketsSessionsContext.isLogined(request));

        return "purchasesPage";
    }


    public void createPurchase(Item item,
                               int itemAmount,
                               Customer customer) {

        Purchase purchase = new Purchase();
        purchase.setState(PurchaseState.CREATED);
        purchase.setCustomerBought(customer);
        purchase.setDateChanged(new Date());
        purchase.setItemBought(item);
        purchase.setItemsAmount(itemAmount);

        purchaseRepo.save(purchase);

    }


    @RequestMapping(value = "basket", method = RequestMethod.GET)
    public String showBasket(Model model,
                             HttpServletRequest request) {

        model.addAttribute("basket",
                customerBasketsSessionsContext.getBasket(request));
        model.addAttribute("showPurch", customerBasketsSessionsContext.isLogined(request));

        return "basketPage";
    }

    @RequestMapping(value = "basket/moveItem", method = RequestMethod.GET)
    public String moveItemFromBasket(@RequestParam(value = "itemAmountEntryId") int itemAmountEntryId,
                                     Model model,
                                     HttpServletRequest request) {

        Basket basket = customerBasketsSessionsContext.getBasket(request);

        basket.getItemAmountEntries().remove(itemAmountEntryRepo.findOne(itemAmountEntryId));

        customerBasketsSessionsContext.basketValueChanged(request, basket);

        model.addAttribute("basket", basket);
        model.addAttribute("showPurch", customerBasketsSessionsContext.isLogined(request));

        return "basketPage";
    }

    @RequestMapping(value = "basket/makePurchase", method = RequestMethod.POST)
    public String createPurchasesFromBasket(Model model,
                                            HttpServletRequest request) {

        if (!customerBasketsSessionsContext.isLogined(request))
            return "phoneName";
        Basket basket = customerBasketsSessionsContext.getBasket(request);

        for (ItemAmountEntry itemAmountEntry : basket.getItemAmountEntries()) {
            createPurchase(itemAmountEntry.getItem(), itemAmountEntry.getAmount(),
                    customerBasketsSessionsContext.getCustomer(request));
        }

        basket.getItemAmountEntries().clear();
        customerBasketsSessionsContext.basketValueChanged(request, basket);

        model.addAttribute("basket", basket);
        return "redirect:../purchase";
    }

    @RequestMapping(value = "basket/login", method = RequestMethod.POST)
    public String customerLogin(Model model,
                                @RequestParam(name = "name") String name,
                                @RequestParam(name = "phone") String phone,
                                HttpServletRequest request) {

        customerBasketsSessionsContext.phoneEntered(request, phone, name);

        return createPurchasesFromBasket(model, request);
    }


    @RequestMapping(value = "basket/addItem", method = RequestMethod.GET)
    public String addItemToBasket(@RequestParam(value = "itemId") int itemId,
                                  Model model,
                                  HttpServletRequest request) {

        request.getSession().getId();

        Basket basket = customerBasketsSessionsContext.getBasket(request);
        model.addAttribute("basket", basket);

        for (ItemAmountEntry itemAmountEntry : basket.getItemAmountEntries()) {
            if (itemAmountEntry.getItem().getId() == itemId) {
                itemAmountEntry.setAmount(itemAmountEntry.getAmount() + 1);
                if (customerBasketsSessionsContext.isLogined(request)) itemAmountEntryRepo.save(itemAmountEntry);
                return "redirect:../basket";
            }
        }

        ItemAmountEntry iae = new ItemAmountEntry(itemRepository.findOne(itemId), 1);
        basket.getItemAmountEntries().add(iae);
        if (customerBasketsSessionsContext.isLogined(request)) itemAmountEntryRepo.save(iae);

        basketPepo.save(basket);

        return "redirect:../basket";
    }


}
