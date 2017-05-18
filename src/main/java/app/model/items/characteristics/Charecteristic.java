package app.model.items.characteristics;


import app.model.items.Categoriya;

import javax.persistence.*;
import java.util.List;

@Entity
public class Charecteristic {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    @Enumerated
    private CharacteristicType type;


    @OneToMany(mappedBy = "charecteristic", cascade = CascadeType.REMOVE)
    private List<CharacteristicValue> characteristicValues;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoriya categoriya;

    @Column
    private boolean showInFilter = true;


    public Charecteristic() {
    }

    public Charecteristic(String name, CharacteristicType type, Categoriya category, boolean showInFilter) {
        this.name = name;
        this.type = type;
        this.categoriya = category;
        this.showInFilter = showInFilter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacteristicType getType() {
        return type;
    }

    public void setType(CharacteristicType type) {
        this.type = type;
    }

    public boolean isShowInFilter() {
        return showInFilter;
    }

    public void setShowInFilter(boolean showInFilter) {
        this.showInFilter = showInFilter;
    }

    public Categoriya getCategoriya() {
        return categoriya;
    }

    public void setCategoriya(Categoriya category) {
        this.categoriya = category;
    }

    public List<CharacteristicValue> getCharacteristicValues() {
        return characteristicValues;
    }

    public void setCharacteristicValues(List<CharacteristicValue> characteristicValues) {
        this.characteristicValues = characteristicValues;
    }

    @Override
    public String toString() {
        return "Charecteristic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", category=" + categoriya +
                ", showInFilter=" + showInFilter +
                '}';
    }
}

