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
    private final Map<String, Item> inventory;
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
    
    public boolean inInventory(Item item){
        return inventory.values().stream().anyMatch(i -> (i == item));
    }//inInventory(Item item)
    
    public void showInventory(){
        System.out.println(Main.D2);
        if (!getInventory().isEmpty()){
            System.out.println("Inventory");
            inventory.values().forEach(i -> {
                System.out.println(i.getName() + " - " + i.getLongDescription());
            });
        } else
            System.out.println("Inventory is empty.");
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
    
    public void take(Item item){
        if (item.isTakeable()){
            if (!inventory.containsKey(item.getName())){
                item.setUsable(true);
                inventory.put(item.getName(), item);
            } else
                System.out.println(item.getName() + " already in inventory.");
        }//if
        else
            System.out.println("Cannot take " + item.getName());
    }//take(String item)
    
    public void place(String item){
        System.out.println("Method under construction.");
    }//place(String item)
    
    public void use(Item item){
        if (item.isUsable(currentRoom)){
            item.changeStatus();
            System.out.println("Used " + item.getName());
        } else
            System.out.println(item.getName() + " cannot be used at this time.");
    }//use(Item item)
    
    public void turn(String direction){
        if(!direction.equals(heading) && !direction.equals("up") && !direction.equals("down"))
            setHeading(direction);
    }//turn(String direction)
    
}//Player
