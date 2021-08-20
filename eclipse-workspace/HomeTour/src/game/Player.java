package game;

import fixtures.*;
import java.util.*;

/**
 *
 * @author Pat Down
 */
public class Player {
    public static final int CAPACITY = 5;
    private Room currentRoom;
    private static Map<String, Item> inventory;
    private String heading;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
        heading = "north";
        inventory = new HashMap<>();
    }//constructor
    
    public Room getCurrentRoom() {
        return currentRoom;
    }//getCurrentRoom
    
    public void setCurrentRoom(Room currentRoom){
        this.currentRoom = currentRoom;
    }//setCurrentRoom
    
    public Map<String, Item> getInventory(){
        return inventory;
    }//getInventory
    
    public void removeInventory(Item item){
        inventory.remove(item.getName(), item);
    }//removeInventory(Item item)
    
    public boolean inInventory(String name){
        return inventory.values().stream().anyMatch(i -> (i.getName().equalsIgnoreCase(name)));
    }//inInventory(Item item)
    
    public Item getInventoryItem(String name){
        for (Item i : inventory.values()){
            if (i.getName().equalsIgnoreCase(name))
                name = i.getName();
        }//for
        return inventory.get(name);
    }//getInventoryItem(String name)
    
    public void showInventory(){
        if (!getInventory().isEmpty()){
            System.out.println("Inventory");
            inventory.values().forEach(i -> {
                System.out.println(i.getName() + " - " + i.getLongDescription());
            });
        } else
            System.out.println("Inventory is empty.");
        System.out.println(Main.D2);
    }//showInventory
    
    public String getHeading(){
        return heading;
    }//getHeading
    
    public void setHeading(String heading){
        this.heading = heading;
    }//setHeading
    
    public void go(String direction){
        if (currentRoom.hasExit(direction)){
            Room nextRoom = currentRoom.getExit(direction);
            turn(direction);
            setCurrentRoom(nextRoom);
        } else
           System.out.println("No exit in this direction.");
    }//go(String direction)
    
    public void look(){
        System.out.println("Method under construction.");
    }//look()
    
    public void look(String direction){
        System.out.println("Method under construction.");
    }//look(String direction)
    
    public void take(String name){
        if (Main.manager.isItem(name)){
            if (currentRoom.hasItem(name)){
                Item item = currentRoom.getItem(name);
                if (item.isTakeable()){
                    if (!inventory.containsKey(item.getName())){
                        item.setUsable(true);
                        inventory.put(item.getName(), item);
                        currentRoom.removeItem(item);
                        System.out.println("Placed " + item.getName().toLowerCase() + " in inventory.");
                    } else
                        System.out.println(item.getName() + " already in inventory.");
                } else
                    System.out.println("Cannot take " + item.getName().toLowerCase());
            } else
                System.out.println("Cannot find " + name);
        } else
            invalidItem(name);
    }//take(String item)
    
    public void place(String name){
        if (Main.manager.isItem(name)){
            if (!currentRoom.hasItem(name)){
                if (inInventory(name)){
                    Item item = getInventoryItem(name);
                    currentRoom.addItem(item);
                    inventory.remove(item.getName(), item);
                    System.out.println("Placed " + item.getName().toLowerCase() + " in " + currentRoom.getName());
                } else
                    System.out.println(name + " not in inventory.");
            } else
                System.out.println("Cannot place " + name + " in room.");
        } else
            invalidItem(name);
        
    }//place(String item)
    
    public void use(String name){
        if (Main.manager.isItem(name)){
            if (currentRoom.hasItem(name)){
                Item item = currentRoom.getItem(name);
                if (item.isUsable(currentRoom)){
                    item.changeStatus();
                    System.out.println("Used " + item.getName());
                } else
                    System.out.println("Cannot use " + name + " at this time.");
            } else if (inInventory(name)){
                Item item = getInventoryItem(name);
                if (item.isUsable(currentRoom)){
                    item.changeStatus();
                    System.out.println("Used " + item.getName());
                } else
                    System.out.println("Cannot use " + name + " at this time.");
            } else
                System.out.println("Cannot access " + name);
        } else
            invalidItem(name);
    }//use(String name)
    
    public void turn(String direction){
        if(!direction.equals(heading) && !direction.equals("up") && !direction.equals("down"))
            setHeading(direction);
    }//turn(String direction)
    
    public void invalidItem(String name){
        System.out.println(name + " is not a valid item.");
    }//invalidItem(String name)
}//Player
