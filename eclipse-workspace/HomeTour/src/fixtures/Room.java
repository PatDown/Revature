package fixtures;

import game.Main;
import java.util.*;

/**
 *
 * @author Pat Down
 */
public class Room extends Fixture {    
    private final Map<String, String> exits;
    private Map<String, Item> items;
    private final Item lights = new Item("Lights", "The lights.", "The lighting of the room.");
    
    public Room(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
        this.exits = new HashMap<>();
        this.items = new HashMap<>();
        this.lights.setStatus(new String[]{"On", "Off"});
        this.lights.setItemRoom(new Room[]{this});
        this.lights.setUsable(true);
        this.lights.setTakeable(false);
        this.items.put(lights.getName(), lights);
    }//constructor
    
    public Map<String, String> getExits(){
        return exits;
    }//getExits
    
    public void setExits(String[] exitArray){
        for (int i = 0; i < exitArray.length; i+=2){
            this.exits.put(exitArray[i], exitArray[i+1]);
        }//for
    }//setExits
    
    public Room getExit(String direction){
        Room[] directionExits = new Room[3];
        int exitNum = 0;
        for(var e : exits.entrySet()){
            if (e.getValue().equals(direction))
                directionExits[exitNum++] = Main.manager.getRooms().get(e.getKey());
        }//for
        
        if (exitNum < 2)
            return directionExits[0];
        else {
            int selection = 0;
            do{
                System.out.println(Main.D2);
                System.out.println("Select the path you would like: ");
                System.out.println("1. " + directionExits[0].getName() +
                                   "\n2. " + directionExits[1].getName());
                selection = Main.input.nextInt();
                Main.input.nextLine();
            }while(selection != 1 && selection != 2);
            return directionExits[selection-1];
        }//else
    }//getExit
    
    public boolean hasExit(String direction){
        return exits.containsValue(direction);
    }//hasExit(String direction)
    
    public void printExits(){
        System.out.println("Exits");
        getExits().entrySet().forEach(e -> {
            System.out.println(e.getKey() + " - " + e.getValue());
        });
    }//printExits
    
    public Map<String, Item> getItems(){
        return items;
    }//getItems
    
    public void setItems(Item item){
        this.items.put(item.getName(), item);
    }//setItems
    
    public void printItems(){
        System.out.println("Items");
        getItems().values().forEach(e -> {
            System.out.println(e.toString());
        });
        System.out.println(Main.D2);
    }//printItems
    
    @Override
    public String toString(){
        StringBuilder roomString = new StringBuilder();
        roomString.append(getName());
        roomString.append("\n");
        roomString.append(getLongDescription());
        roomString.append("\n");
        roomString.append(Main.D2);
        return roomString.toString();
    }//toString override
}//Room
