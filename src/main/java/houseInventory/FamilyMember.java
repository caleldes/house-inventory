package houseInventory;

import java.util.List;
import java.util.stream.Collectors;

public class FamilyMember extends Person{

    public FamilyMember(String name, List<Item> items) {
        super(name, items);
    }

    public FamilyMember(String name) {
        super(name);
    }

    @Override
    public List<ItemInfo> getItemsInfo() {
        return getItems().stream()
                .map(item -> new ItemInfo(item,this,ItemStatus.uzywany))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "FamilyMember{" +
                "name='" + name + '\'' +
                '}';
    }
}
