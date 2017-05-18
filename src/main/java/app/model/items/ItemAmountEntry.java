package app.model.items;


import app.model.items.Item;

import javax.persistence.*;

@Entity
public class ItemAmountEntry {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "id")
    private Item item;

    @Column
    private int amount;

    public ItemAmountEntry() {
    }

    public ItemAmountEntry(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
