package game;

import fixtures.Item;
import fixtures.Room;
import java.util.Map;

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
        heading = "North";
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
        switch (direction){
            case "forward":
                break;
            case "back":
                turn("around");
                break;
            case "left":
                turn(direction);
                break;
            case "right":
                turn(direction);
                break;
            case "up":
                break;
            case "down":
                break;
            default:
                break;
        }//switch (direction)
        System.out.println("Method under construction.");
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
        switch (direction){
            case "around":
                switch (heading){
                    case "North":
                        heading = "South";
                        break;
                    case "South":
                        heading = "North";
                        break;
                    case "East":
                        heading = "West";
                        break;
                    case "West":
                        heading = "East";
                        break;
                    default:
                        System.out.println("Something went wrong.");
                        break;
                }//switch (heading
                break;
            case "left":
                switch (heading){
                    case "North":
                        heading = "West";
                        break;
                    case "South":
                        heading = "East";
                        break;
                    case "East":
                        heading = "North";
                        break;
                    case "West":
                        heading = "South";
                        break;
                    default:
                        System.out.println("Something went wrong.");
                        break;
                }//switch (heading
                break;
            case "right":
                switch (heading){
                    case "North":
                        heading = "West";
                        break;
                    case "South":
                        heading = "East";
                        break;
                    case "East":
                        heading = "South";
                        break;
                    case "West":
                        heading = "North";
                        break;
                    default:
                        System.out.println("Something went wrong.");
                        break;
                }//switch (heading
                break;
            default:
                System.out.println("Something went wrong.");
                break;
        }//switch (direction)
    }//turn()
}//Player
