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
    }//Player(Room)
    
    public Room getCurrentRoom() {
        return currentRoom;
    }//getCurrentRoom()
    
    public void setCurrentRoom(Room currentRoom){
        this.currentRoom = currentRoom;
    }//setCurrentRoom(Room)
    
    public Map<String, Item> getInventory(){
        return inventory;
    }//getInventory()
    
    public void removeInventory(Item item){
        inventory.remove(item.getName(), item);
    }//removeInventory(Item)
    
    public boolean inInventory(String name){
        return inventory.values().stream().anyMatch(i -> (i.getName().equalsIgnoreCase(name)));
    }//inInventory(Item)
    
    public Item getInventoryItem(String name){
        for (Item i : inventory.values())
            if (i.getName().equalsIgnoreCase(name))
                name = i.getName();
        return inventory.get(name);
    }//getInventoryItem(String)
    
    public void showInventory(){
        System.out.println("Inventory");
        inventory.values().forEach(i -> {
            System.out.println(i.getName() + " - " + i.getCurrentStatus());
        });
        System.out.println(Main.D2);
    }//showInventory()
    
    public String getHeading(){
        return heading;
    }//getHeading()
    
    public void setHeading(String heading){
        this.heading = heading;
    }//setHeading(String)
    
    public void go(String direction){
        if (currentRoom.hasExit(direction)){
            Room nextRoom = currentRoom.getExit(direction);
            turn(direction);
            setCurrentRoom(nextRoom);
        } else
           System.out.println("No exit in this direction.");
    }//go(String)
    
    public void look(String command){
        System.out.println("Method under construction.");
    }//look(String)
    
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
    }//take(String)
    
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
    }//place(String)
    
    public void use(String name){
        if (Main.manager.isItem(name) || currentRoom.hasItem(name)){
            if (inInventory(name)){
                Item item = getInventoryItem(name);
                item.changeStatus();
                System.out.println("Used " + item.getName());    
            } else if (currentRoom.hasItem(name)){
                Item item = currentRoom.getItem(name);
                if (item.isUsable(currentRoom)){
                    item.changeStatus();
                    System.out.println("Used " + item.getName());
                } else
                    System.out.println("Cannot use " + name + " at this time.");
            } else
                System.out.println("Cannot access " + name);
        } else
            invalidItem(name);
    }//use(String)
    
    public void turn(String direction){
        switch(direction) {
            case "north":
            case "east":
            case "south":
            case "west":
                setHeading(direction);
                break;
            case "right":
                switch(heading){
                    case "north":
                        setHeading("north");
                        break;
                    case "east":
                        setHeading("east");
                        break;
                    case "south":
                        setHeading("west");
                        break;
                    case "west":
                        setHeading("north");
                        break;
                }//switch(heading)
                break;
            case "left":
                switch(heading){
                    case "north":
                        setHeading("west");
                        break;
                    case "east":
                        setHeading("north");
                        break;
                    case "south":
                        setHeading("east");
                        break;
                    case "west":
                        setHeading("south");
                        break;
                }//switch(heading)
                break;
            case "around":
                switch(heading){
                    case "north":
                        setHeading("south");
                        break;
                    case "east":
                        setHeading("west");
                        break;
                    case "south":
                        setHeading("north");
                        break;
                    case "west":
                        setHeading("east");
                        break;
                }//switch(heading)
                break;
            case "up":
            case "down":
                break;
            default:
                System.out.println("Cannot turn " + direction);
                break;
        }//switch(direction)
    }//turn(String)
    
    public void invalidItem(String name){
        System.out.println(name + " is not a valid item.");
    }//invalidItem(String)
}//Player
