package app.mvc_controllers.item;

import app.model.items.characteristics.CharacteristicValue;
import app.model.items.characteristics.Charecteristic;

public class CharacteristicWithAvalableValues {
    private Charecteristic charecteristic;
    private Iterable<CharacteristicValue> values;
    private int currentCharsValueId;

    public CharacteristicWithAvalableValues() {
    }

    public CharacteristicWithAvalableValues(Charecteristic charecteristic, Iterable<CharacteristicValue> values) {
        this.charecteristic = charecteristic;
        this.values = values;
    }

    public CharacteristicWithAvalableValues(Charecteristic charecteristic, Iterable<CharacteristicValue> values, int currentCharsValueId) {
        this.charecteristic = charecteristic;
        this.values = values;
        this.currentCharsValueId = currentCharsValueId;
    }

    public int getCurrentCharsValueId() {
        return currentCharsValueId;
    }

    public void setCurrentCharsValueId(int currentCharsValueId) {
        this.currentCharsValueId = currentCharsValueId;
    }

    public Charecteristic getCharecteristic() {
        return charecteristic;
    }

    public void setCharecteristic(Charecteristic charecteristic) {
        this.charecteristic = charecteristic;
    }

    public Iterable<CharacteristicValue> getValues() {
        return values;
    }

    public void setValues(Iterable<CharacteristicValue> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "CharacteristicWithAvalableValues{" +
                "charecteristic=" + charecteristic +
                ", values=" + values +
                '}';
    }
}
