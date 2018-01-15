package houseInventory;

import java.util.List;
import java.util.stream.Collectors;

public class FamilyFriend extends Person{

    protected final String lastName;
    protected final String phoneNumber;

    public FamilyFriend(String name, List<Item> items, String lastName, String phoneNumber) {
        super(name, items);
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public FamilyFriend(String name, String lastName, String phoneNumber) {
        super(name);
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public List<ItemInfo> getItemsInfo() {
        return getItems().stream()
                .map(item -> new ItemInfo(item,this,ItemStatus.pozyczony))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "FamilyFriend{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FamilyFriend that = (FamilyFriend) o;

        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        return phoneNumber != null ? phoneNumber.equals(that.phoneNumber) : that.phoneNumber == null;
    }
}
