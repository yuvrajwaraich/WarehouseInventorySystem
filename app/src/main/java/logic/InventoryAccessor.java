package logic;

import database.IDBLayer;
import database.DatabaseManager;
import database.PersistenceException;
import objects.IDSO;
import objects.Inventory;

public class InventoryAccessor {
    private IDBLayer InventoryDB;

    // default constructor
    public InventoryAccessor(){
        this.InventoryDB = DatabaseManager.getInventoryPersistence();
    }

    // constructor
    public InventoryAccessor(IDBLayer db) {
        this.InventoryDB = db;
    }

    public Inventory createInventory(String name){
        Inventory newInv = new Inventory(-1, name);
        int id = InventoryDB.create(newInv);
        newInv = (Inventory) InventoryDB.get(id);
        if (newInv == null){
            System.out.println("Null Inventory");
        }
        return newInv;
    }

    public Inventory getInventory(int id){
        Inventory gotItem = InventoryDB.get(id);
        if (gotItem == null){
            System.out.println("Null Inventory");
        }
        return gotItem;
    }

    public void deleteInventory(int id){
        InventoryDB.delete(id);
    }

    public Inventory[] getAllInventories(){
        IDSO[] invsAsIDSO = InventoryDB.getDB();
        Inventory[] inventories = new Inventory[invsAsIDSO.length];

        for (int i = 0; i < invsAsIDSO.length; i++){
            inventories[i] = (Inventory) invsAsIDSO[i];
        }

        return inventories;
    }

    public void deleteAllInventories(){
        InventoryDB.clearDB();
    }
}