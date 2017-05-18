package app.model.items;


import app.model.users.Admin;
import app.model.users.Customer;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Purchase {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "phone")
    private Customer customerBought;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "login")
    private Admin seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Item itemBought;

    @Column
    private int itemsAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateChanged;

    @Enumerated
    private PurchaseState state;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomerBought() {
        return customerBought;
    }

    public void setCustomerBought(Customer customerBought) {
        this.customerBought = customerBought;
    }

    public Admin getSeller() {
        return seller;
    }

    public void setSeller(Admin seller) {
        this.seller = seller;
    }

    public Item getItemBought() {
        return itemBought;
    }

    public void setItemBought(Item itemBought) {
        this.itemBought = itemBought;
    }

    public int getItemsAmount() {
        return itemsAmount;
    }

    public void setItemsAmount(int itemsAmount) {
        this.itemsAmount = itemsAmount;
    }

    public Date getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }

    public PurchaseState getState() {
        return state;
    }

    public void setState(PurchaseState state) {
        this.state = state;
    }
}

