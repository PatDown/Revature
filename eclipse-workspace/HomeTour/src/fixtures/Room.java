package fixtures;

import game.Main;
import game.Player;
import java.util.*;

/**
 *
 * @author Pat Down
 */
public class Room extends Fixture {
    private final Map<Room, String> exits;
    
    public Room(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
        this.exits = new HashMap<>();
    }//constructor
    
    public Map<Room, String> getExits(){
        return exits;
    }//getExits*/
    
    public final void setExits(String[] exitArray){
        for (int i = 0; i < exitArray.length; i+=2){
            this.exits.put(Main.manager.housePlan.get(exitArray[i]), exitArray[i+1]);
        }//for
    }//setExits
    
    public Room getExit(String direction){
        Room[] directionExits = new Room[2];
        int exitNum = 0;
        for(var e : exits.entrySet()){
            if (e.getValue().equals(direction))
                directionExits[exitNum++] = e.getKey();
        }//for
        if (exitNum > 2){
            return directionExits[0];
        } else {
            int selection = 0;
            do{
                System.out.println("Select the path you would like: ");
                System.out.println("1. " + directionExits[0].getName() +
                                   "2. " + directionExits[1].getName());
                selection = Main.input.nextInt();
            }while(selection != 1 && selection != 2);
            return directionExits[selection-1];
        }//else
    }//getExit
    
    public boolean hasExit(String direction){
        return exits.containsValue(direction);
    }//hasExit(String direction)
    
    public void printExits(){
        for (var e : exits.entrySet()){
            System.out.println(e.getValue() + " - " + e.getKey().getName());
        }//for
    }//printExits
    
    @Override
    public String toString(){
        StringBuilder roomString = new StringBuilder();
        roomString.append(getName());
        roomString.append("\n\n");
        roomString.append(getLongDescription());
        
        return roomString.toString();
    }//toString override
}//Room
