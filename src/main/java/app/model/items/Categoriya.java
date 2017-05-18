package app.model.items;


import javax.persistence.*;
import java.util.List;

import app.model.items.characteristics.Charecteristic;

@Entity
public class Categoriya {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<Categoriya> subcategories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Categoriya parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriya", cascade = CascadeType.REMOVE)
    private List<Charecteristic> charecteristics;

    @Column
    private String name;

    @OneToMany(mappedBy = "categoriya", cascade = CascadeType.REMOVE)
    private List<Item> items;

    public Categoriya() {
    }

    public Categoriya(Categoriya parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Categoriya> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Categoriya> subcategories) {
        this.subcategories = subcategories;
    }

    public Categoriya getParent() {
        return parent;
    }

    public void setParent(Categoriya parent) {
        this.parent = parent;
    }

    public List<Charecteristic> getCharecteristics() {
        return charecteristics;
    }

    public void setCharecteristics(List<Charecteristic> charecteristics) {
        this.charecteristics = charecteristics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
