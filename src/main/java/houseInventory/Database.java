package houseInventory;

public interface Database {
    void saveInventory(Inventory inventory);
    Inventory getInventory();
}
