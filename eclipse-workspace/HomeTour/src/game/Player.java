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
    private Map<String, Item> inventory;
    private String heading;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
        heading = "north";
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
    
    public void open(){
        System.out.println("Method under construction.");
    }//open()
    
    public void look(){
        System.out.println("Method under construction.");
    }//look()
    
    public void look(String direction){
        System.out.println("Method under construction.");
    }//look(String direction)
    
    public void take(String item){
        System.out.println("Method under construction.");
    }//take(String item)
    
    public void place(String item){
        System.out.println("Method under construction.");
    }//place(String item)
    
    public void use(String item){
        System.out.println("Method under construction.");
    }//use(String item)
    
    public void turn(String direction){
        if(!direction.equals(heading) && !direction.equals("up") && !direction.equals("down"))
            setHeading(direction);
    }//turn()
}//Player
