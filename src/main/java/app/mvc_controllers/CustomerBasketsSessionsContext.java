package app.mvc_controllers;

import app.model.items.Basket;
import app.model.items.ItemAmountEntry;
import app.model.users.Customer;
import app.repository.BasketPepo;
import app.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomerBasketsSessionsContext {

    private Map<String, Basket> sessionsBasketsMap = new ConcurrentHashMap<>();

    private Set<String> loginedSessions = new HashSet<>();

    private Map<String, Customer> sessionsCustomersMap = new ConcurrentHashMap<>();

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private BasketPepo basketPepo;


    public Map<String, Basket> getSessionsBasketsMap() {
        return sessionsBasketsMap;
    }

    public Basket getBasket(HttpServletRequest request) {
        String session = request.getSession().getId();

        /*if user entered phone*/
        if (loginedSessions.contains(session)) return sessionsCustomersMap.get(session).getBasket();

        /*if user comes first time to service*/
        if (sessionsBasketsMap.get(session) == null) {
            Basket sessionBasket = new Basket();
            sessionBasket.setItemAmountEntries(new ArrayList<>());
            sessionsBasketsMap.put(session, sessionBasket);
        }
        return sessionsBasketsMap.get(session);
    }

    public void basketValueChanged(HttpServletRequest request, Basket basket) {
        String session = request.getSession().getId();
        if (loginedSessions.contains(session)) {
            basketPepo.save(basket);
            Customer customer = sessionsCustomersMap.get(session);
            customer.setBasket(basket);
            customerRepo.save(customer);
        }
    }

    public void phoneEntered(HttpServletRequest request, String phone, String name) {
        String session = request.getSession().getId();
        try {
            loginedSessions.add(session);
            Customer fromRepo = customerRepo.findOne(phone);
            if (fromRepo == null) {
                Customer newCustomer = new Customer(phone, name);
                Basket basket = sessionsBasketsMap.get(session);
                newCustomer.setBasket(basket);
                basket.setOwner(newCustomer);

                basketPepo.save(basket);
                sessionsCustomersMap.put(session, customerRepo.save(newCustomer));
            } else {
                sessionsCustomersMap.put(session, fromRepo);
            }
        } catch (Exception e) {
            loginedSessions.remove(session);
        }
    }

    public boolean isLogined(HttpServletRequest request) {
        String session = request.getSession().getId();
        return loginedSessions.contains(session);
    }

    public Customer getCustomer(HttpServletRequest request) {
        String session = request.getSession().getId();
        return sessionsCustomersMap.get(session);
    }

}
