package app.model.items.characteristics;

import app.model.items.Categoriya;
import app.model.items.Item;

import javax.persistence.*;
import java.util.List;

@Entity
public class CharacteristicValue {

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany(mappedBy = "characteristicValues")
    private List<Item> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Charecteristic charecteristic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Categoriya category;

    @Column
    private String value;

    public CharacteristicValue() {
    }

    public CharacteristicValue(Charecteristic charecteristic, Categoriya category, String value) {
        this.charecteristic = charecteristic;
        this.category = category;
        this.value = value;
    }

    public CharacteristicValue(Charecteristic charecteristic, String value) {
        this.charecteristic = charecteristic;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Categoriya getCategory() {
        return category;
    }

    public void setCategory(Categoriya category) {
        this.category = category;
    }

    public Charecteristic getCharecteristic() {
        return charecteristic;
    }

    public void setCharecteristic(Charecteristic charecteristic) {
        this.charecteristic = charecteristic;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CharacteristicValue{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
