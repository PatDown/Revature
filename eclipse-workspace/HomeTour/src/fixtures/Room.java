package fixtures;

import game.Main;
import java.util.*;

/**
 *
 * @author Pat Down
 */
public class Room extends Fixture {    
    private final Map<Room, String> exits;
    private final Map<String, Item> items;
    
    public Room(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
        exits = new HashMap<>();
        items = new HashMap<>();
    }//Room(String name, String shortDescription, String longDescription)
    
    public Map<Room, String> getExits(){
        return exits;
    }//getExits()
    
    public void setExits(String[] exitArray){
        for (int i = 0; i < exitArray.length; i+=2)
            this.exits.put(Main.manager.getRoom(exitArray[i]), exitArray[i+1]);
    }//setExits(String[] exitArray)
    
    public Room getExit(String direction){
        Room[] directionExits = new Room[3];
        int exitNum = 0;
        for(var e : exits.entrySet())
            if (e.getValue().equals(direction))
                directionExits[exitNum++] = Main.manager.getRoom(e.getKey().getName());
        
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
    }//getExit(String direction)
    
    public boolean hasExit(String direction){
        return exits.containsValue(direction);
    }//hasExit(String direction)
    
    public void printExits(){
        System.out.println("Exits\n");
        StringBuilder northExits = new StringBuilder();
        StringBuilder southExits = new StringBuilder();
        StringBuilder eastExits = new StringBuilder();
        StringBuilder westExits = new StringBuilder();
        StringBuilder upExits = new StringBuilder();
        StringBuilder downExits = new StringBuilder();
        
        getExits().entrySet().forEach(e -> {
            switch(e.getValue()){
                case "north":
                    northExits.append("\n\t");
                    northExits.append(e.getKey().getName());
                    northExits.append(": ");
                    northExits.append(e.getKey().getShortDescription());
                    break;
                case "south":
                    southExits.append("\n\t");
                    southExits.append(e.getKey().getName());
                    southExits.append(": ");
                    southExits.append(e.getKey().getShortDescription());
                    break;
                case "east":
                    eastExits.append("\n\t");
                    eastExits.append(e.getKey().getName());
                    eastExits.append(": ");
                    eastExits.append(e.getKey().getShortDescription());
                    break;
                case "west":
                    westExits.append("\n\t");
                    westExits.append(e.getKey().getName());
                    westExits.append(": ");
                    westExits.append(e.getKey().getShortDescription());
                    break;
                case "up":
                    upExits.append("\n\t");
                    upExits.append(e.getKey().getName());
                    upExits.append(": ");
                    upExits.append(e.getKey().getShortDescription());
                    break;
                case "down":
                    downExits.append("\n\t");
                    downExits.append(e.getKey().getName());
                    downExits.append(": ");
                    downExits.append(e.getKey().getShortDescription());
                    break;
                default:
                    System.out.println("Something went wrong in the printExits method.");
                    break;
            }//switch(e.getValue())
        });
        if (northExits.length() > 0)
            System.out.println("North" + northExits.toString());
        if (southExits.length() > 0)
            System.out.println("South" + southExits.toString());
        if (eastExits.length() > 0)
            System.out.println("East" + eastExits.toString());
        if (westExits.length() > 0)
            System.out.println("West" + westExits.toString());
        if (upExits.length() > 0)
            System.out.println("Up" + upExits.toString());
        if (downExits.length() > 0)
            System.out.println("Down" + downExits.toString());
    }//printExits()
    
    public Map<String, Item> getItems(){
        return items;
    }//getItems()
    
    public void addItem(Item item){
        if (!items.containsKey(item.getName()))
            items.put(item.getName(), item);
        else
            System.out.println(item.getName() + " cannot be placed in this room.");
    }//addItem(Item item)
    
    public void removeItem(Item item){
        items.remove(item.getName());
    }//removeItem(Item item)
    
    public Item getItem(String name){
        for (Item i : items.values())
            if (i.getName().equalsIgnoreCase(name))
                name = i.getName();
        return items.get(name);
    }//getItem(String name)
    
    public boolean hasItem(String name){
        return items.keySet().stream().anyMatch(s -> (s.equalsIgnoreCase(name)));
    }//hasItem(String name)
    
    public void printItems(){
        System.out.println("Items");
        getItems().values().forEach(e -> {
            System.out.println("\t" + e.toString());
        });
        System.out.println(Main.D2);
    }//printItems()
    
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
