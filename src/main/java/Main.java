import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import houseInventory.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(Person.class, new AbstractElementAdapter());
        Gson gson = gsonBilder.create();

        Database database = new FileDatabase(gson);
        Inventory inventory = database.getInventory();
        repl(inventory);
        database.saveInventory(inventory);
    }

    private static void repl(Inventory inventory){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try{
                System.out.print("$ ");
                String input = null;
                input = bufferedReader.readLine();

                String[] args =  input.split(" ");

                if(args.length < 1) throw new IllegalArgumentException("Potrzeba 1 argumentu");

                switch (args[0]){
                    case "Quit":
                        return;
                    case "Add":
                        add(inventory,args);
                        break;
                    case "Help":
                        help(inventory,args);
                        break;
                    case "Remove":
                        remove(inventory,args);
                        break;
                    case "Show":
                        show(inventory,args);
                        break;
                    case "Assign":
                        assign(inventory,args);
                        break;
                    case "DeAssign":
                        deAssign(inventory,args);
                        break;
                }
            }
            catch (Exception e){
                System.out.println("Error :" + e.getMessage());
            }

        }
    }

    private static void deAssign(Inventory inventory, String[] args) {

        if(args.length < 3) throw new IllegalArgumentException("Potrzeba 3 argumentu");
        Person person = inventory.findPersonByName(args[1]);
        Item item = inventory.findItemByName(args[2]);
        inventory.deAssign(item,person);
    }

    private static void assign(Inventory inventory, String[] args) {

        if(args.length < 3) throw new IllegalArgumentException("Potrzeba 3 argumentu");
        Person person = inventory.findPersonByName(args[1]);
        Item item = inventory.findItemByName(args[2]);
        inventory.assign(item,person);
    }

    private static void show(Inventory inventory, String[] args) {
        if(args.length < 2) throw new IllegalArgumentException("Potrzeba 2 argumentu");
        switch (args[1]){
            case "Person":
                List<Person> personList = inventory.getPeople();
                personList.forEach(System.out::println);
                break;
            case "Family":
                List<FamilyMember> familyMembers = inventory.getFamily();
                familyMembers.forEach(System.out::println);
                break;
            case "Friend":
                List<FamilyFriend> familyFriends = inventory.getFriends();
                familyFriends.forEach(System.out::println);
                break;
            case "Item":
                if(args.length < 3) throw new IllegalArgumentException("Potrzeba 3 argumentu");
                List<Item> items = null;
                switch(args[2]){
                    case "All":
                        items = inventory.getAllItems();
                        break;
                    case "Family":
                        items = inventory.getFamilyItems();
                        break;
                    case "Friend":
                        items = inventory.getFriendsItems();
                        break;
                    case "Available":
                        items = inventory.getAvailableItems();
                        break;
                }
                items.forEach(System.out::println);
                break;
            case "Info":
                if(args.length < 3) throw new IllegalArgumentException("Potrzeba 3 argumentu");
                List<ItemInfo> itemsInfo = null;
                switch(args[2]){
                    case "All":
                        itemsInfo = inventory.getAllItemsInfo();
                        break;
                    case "Family":
                        itemsInfo = inventory.getFamilyItemsInfo();
                        break;
                    case "Friend":
                        itemsInfo = inventory.getFriendsItemsInfo();
                        break;
                    case "Available":
                        itemsInfo = inventory.getAvailableItemsInfo();
                        break;
                }
                itemsInfo.forEach(System.out::println);
                break;
        }
    }

    private static void remove(Inventory inventory, String[] args) {
        if(args.length < 3) throw new IllegalArgumentException("Potrzeba 3 argumentu");
        switch (args[1]){
            case "Person":
                Person person = inventory.findPersonByName(args[2]);
                inventory.remove(person);
                break;
            case "Item":
                Item item = inventory.findItemByName(args[2]);
                inventory.remove(item);
                break;
        }
    }

    private static void help(Inventory inventory, String[] args) {
    }

    private static void add(Inventory inventory, String[] args){
        if(args.length < 3) throw new IllegalArgumentException("Potrzeba 3 argumentu");
        switch (args[1]){
            case "Family":
                Person person = new FamilyMember(args[2]);
                inventory.add(person);
                break;
            case "Friend":
                if(args.length < 5) throw new IllegalArgumentException("Potrzeba 5 argumentu");
                person = new FamilyFriend(args[2],args[3],args[4]);
                inventory.add(person);
                break;
            case "Item":
                Item item = new Item(args[2]);
                inventory.add(item);
                break;
        }
    }
}
