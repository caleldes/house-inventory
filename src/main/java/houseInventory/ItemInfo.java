package houseInventory;

public class ItemInfo {
    private final Item item;
    private final Person person;
    private final ItemStatus itemStatus;

    public ItemInfo(Item item, Person person, ItemStatus itemStatus) {
        this.item = item;
        this.person = person;
        this.itemStatus = itemStatus;
    }

    public Item getItem() {
        return item;
    }

    public Person getPerson() {
        return person;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "item=" + item +
                ", person=" + person +
                ", itemStatus=" + itemStatus +
                '}';
    }
}
