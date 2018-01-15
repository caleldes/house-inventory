package houseInventory;

import java.util.LinkedList;
import java.util.List;

public abstract class Person {

    protected final String name;
    private List<Item> items;

    protected Person(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    protected Person(String name) {
        this(name, new LinkedList<>());
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public abstract List<ItemInfo> getItemsInfo();

    public void add(Item item) {
        if (items.contains(item)) {
            throw new MyException("Osoba ma juz taki przedmiot");
        }
        items.add(item);
    }

    public void remove(Item item) {
        if (items.contains(item)) {
            items.remove(item);
        }
        throw new MyException("Osoba nie ma takiego przedmiotu");
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return name != null ? name.equals(person.name) : person.name == null;
    }
}
