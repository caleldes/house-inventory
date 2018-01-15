package houseInventory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Inventory {

    private List<Item> allItems = new LinkedList<>();

    private List<Item> availableItems = new LinkedList<>();

    private List<Person> people = new LinkedList<>();

    public void add(Item item){
        if(allItems.contains(item)){
            throw new MyException("Inwentarz ma juz taki przedmiot");
        }
        allItems.add(item);
    }

    public void remove(Item item){
        if (!allItems.contains(item)) {
            throw new MyException("Inwentarz nie ma takiego przedmiotu");
        }
        if(availableItems.contains(item)){
            allItems.remove(item);
            availableItems.remove(item);
        }
        else{
            throw new MyException("Przedmiot nie jest na stanie");
        }
    }

    public Person findPersonByName(String name){
        for(Person person : people){
            if(person.getName().equals(name)){
                return person;
            }
        }
        return null;
    }

    public List<Item> getAllItems() {
        return allItems;
    }

    public List<ItemInfo> getAllItemsInfo() {
        List<ItemInfo> items = new LinkedList<>();
        items.addAll(getAvailableItemsInfo());
        items.addAll(getFamilyItemsInfo());
        items.addAll(getFriendsItemsInfo());
        return items;
    }

    public List<Item> getAvailableItems() {
        return availableItems;
    }

    public List<ItemInfo> getAvailableItemsInfo() {
        return availableItems.stream()
                .map(item -> new ItemInfo(item,null,ItemStatus.dostepny))
                .collect(Collectors.toList());
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<FamilyMember> getFamily(){
        return people.stream().filter(FamilyMember.class::isInstance)
                .map(person -> (FamilyMember)person)
                .collect(Collectors.toList());
    }

    public List<Item> getFamilyItems(){
        List<Item> items = new LinkedList<>();
        people.stream().filter(FamilyMember.class::isInstance)
                .map(Person::getItems)
                .forEach(items::addAll);
        return items;
    }

    public List<ItemInfo> getFamilyItemsInfo(){
        List<ItemInfo> items = new LinkedList<>();
        people.stream().filter(FamilyMember.class::isInstance)
                .map(Person::getItemsInfo)
                .forEach(items::addAll);
        return items;
    }

    public List<FamilyFriend> getFriends(){
        return people.stream().filter(FamilyFriend.class::isInstance)
                .map(person -> (FamilyFriend)person)
                .collect(Collectors.toList());
    }

    public List<Item> getFriendsItems(){
        List<Item> items = new LinkedList<>();
        people.stream().filter(FamilyFriend.class::isInstance)
                .map(Person::getItems)
                .forEach(items::addAll);
        return items;
    }

    public List<ItemInfo> getFriendsItemsInfo(){
        List<ItemInfo> items = new LinkedList<>();
        people.stream().filter(FamilyFriend.class::isInstance)
                .map(Person::getItemsInfo)
                .forEach(items::addAll);
        return items;
    }

    public void assign(Item item, Person person){
        if(!people.contains(person)){
            throw new MyException("Nie istnieje podana osoba");
        }
        if(allItems.contains(item)){
            if(availableItems.contains(item)){
                person.add(item);
                availableItems.remove(item);
            }
            else{
                throw new MyException("Przedmiot nie jest na stanie");
            }
        }
        else{
            throw new MyException("Inwentarz nie ma takiego przedmiotu");
        }
    }

    public void deAssign(Item item, Person person){
        if(!people.contains(person)){
            throw new MyException("Nie istnieje podana osoba");
        }
        if(allItems.contains(item)){
            person.remove(item);
            availableItems.add(item);
        }
        else{
            throw new MyException("Inwentarz nie ma takiego przedmiotu");
        }
    }

    public Item findItemByName(String name){
        for(Item item : allItems){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    public void add(Person person){
        if(people.contains(person)){
            throw new MyException("Istnieje juz taka osoba");
        }
        else{
            people.add(person);
        }
    }

    public void remove(Person person){
        if(people.contains(person)){
            availableItems.addAll(person.getItems());
            person.setItems(new LinkedList<>());
            people.remove(person);
        }
        else{
            throw new MyException("Nie ma takiej osoby");
        }
    }
}
