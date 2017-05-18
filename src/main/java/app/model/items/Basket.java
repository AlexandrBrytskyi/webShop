package app.model.items;


import app.model.users.Customer;
import app.model.users.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Basket {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "phone")
    private Customer owner;

    public Basket() {
    }

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ItemAmountEntry> itemAmountEntries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public List<ItemAmountEntry> getItemAmountEntries() {
        return itemAmountEntries;
    }

    public void setItemAmountEntries(List<ItemAmountEntry> itemAmountEntries) {
        this.itemAmountEntries = itemAmountEntries;
    }
}
