package app.model.users;


import app.model.items.Purchase;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Admin extends User {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller")
    private List<Purchase> selledPurchases;

    public Admin() {
    }

    public Admin(String login, String password) {
        super(login, password);
    }

    public List<Purchase> getSelledPurchases() {
        return selledPurchases;
    }

    public void setSelledPurchases(List<Purchase> selledPurchases) {
        this.selledPurchases = selledPurchases;
    }
}
