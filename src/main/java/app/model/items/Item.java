package app.model.items;


import app.model.items.characteristics.CharacteristicValue;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Categoriya.class)
    @JoinColumn(referencedColumnName = "id")
    private Categoriya categoriya;

    @Column
    @Lob
    private byte[] picture;

    @Column
    private String description;


    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "item_charact_value")
    private List<CharacteristicValue> characteristicValues;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "item")
    private List<ItemAmountEntry> itemAmountEntriesInUserBaskets;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "itemBought")
    private List<Purchase> inPurchases;


    @Column
    private BigDecimal price;

    @Column
    private int amountAvailable;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Item() {
    }

    public Item(Categoriya categoriya, byte[] picture, String description, BigDecimal price, int amountAvailable) {
        this.categoriya = categoriya;
        this.picture = picture;
        this.description = description;
        this.price = price;
        this.amountAvailable = amountAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoriya getCategoriya() {
        return categoriya;
    }

    public void setCategoriya(Categoriya categoriya) {
        this.categoriya = categoriya;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CharacteristicValue> getCharacteristicValues() {
        return characteristicValues;
    }

    public void setCharacteristicValues(List<CharacteristicValue> characteristicValues) {
        this.characteristicValues = characteristicValues;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", category=" + categoriya +
                ", description='" + description + '\'' +
                ", characteristicValues=" + characteristicValues +
                ", price=" + price +
                ", amountAvailable=" + amountAvailable +
                ", comments=" + comments +
                '}';
    }
}
