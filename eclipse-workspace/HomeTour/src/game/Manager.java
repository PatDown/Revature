package game;

import fixtures.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Pat Down
 */
public class Manager {
    private static Room startingRoom;
    private final Map<String, Room> rooms = new HashMap<>();
    private static final Map<String, String[]> exitMap = new HashMap<>();
    private static final Map<String, Item> items = new HashMap<>();
    private final Map<String, Room[]> itemRooms = new HashMap<>();
    private final Map<String, String[]> status = new HashMap<>();
    private Scanner fileScanner1;
    private Scanner fileScanner2;
    private final String roomText = new File("resources/Rooms.txt").getAbsolutePath();
    private final String itemText = new File("resources/Items.txt").getAbsolutePath();

    public Room getStartingRoom() {
        return startingRoom;
    }//getStartingRoom
    
    public Map<String, String[]> getExitMap(){
        return exitMap;
    }//getExitMap
    
    public Map<String, Room> getRooms(){
        return rooms;
    }//getRooms
    
    public Room[] getRooms(String[] rooms){
        Room[] roomArray = new Room[rooms.length];
        for (int i = 0; i < rooms.length; i++)
            roomArray[i] = this.rooms.get(rooms[i]);
        return roomArray;
    }//getRooms(String[] rooms)
    
    public Room getRoom(String name){
        return rooms.get(name);
    }//getRoom
    
    public Item getItem(String name){
        return items.get(name);
    }//getItem
    
    public void init() throws NullPointerException{
        try {
            fileScanner1 = new Scanner(new File(roomText));
        }catch(FileNotFoundException e){
            System.out.print(e.getMessage());
        }//catch
        Room foyer = new Room(
            fileScanner1.nextLine().trim(),
            fileScanner1.nextLine().trim(),
            fileScanner1.nextLine().trim().translateEscapes());
        String exits = fileScanner1.nextLine();
        String[] exitsArray = exits.split(", ");
        
        startingRoom = foyer;
        rooms.put(foyer.getName(), foyer);
        exitMap.put(foyer.getName(), exitsArray);
        buildHouse();
        fileScanner1.close();
        try{
            fileScanner2 = new Scanner(new File(itemText));
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }//catch
        Item tv = new Item(
            fileScanner2.nextLine().trim(), 
            fileScanner2.nextLine().trim(), 
            fileScanner2.nextLine().trim().translateEscapes());
        String itemRooms = fileScanner2.nextLine().trim();
        Room[] roomsArray = getRooms(itemRooms.split(", "));
        String actions = fileScanner2.nextLine().trim();
        String[] statusArray = actions.split(", ");
        boolean takeable = fileScanner2.nextBoolean();
        items.put(tv.getName(), tv);
        items.get(tv.getName()).setTakeable(takeable);
        this.itemRooms.put(tv.getName(), roomsArray);
        this.status.put(tv.getName(), statusArray);
        items.get(tv.getName()).setItemRoom(roomsArray);
        items.get(tv.getName()).setStatus(statusArray);
        findItems();
        
        for (var i : rooms.values()){
            for (var j : items.values()){
                if(j.isInRoom(i))
                    i.setItems(j);
            }//for
            System.out.println(i.getName());
            i.printItems();
        }//for
        fileScanner2.close();
    }//init
    
    public void buildHouse() throws NullPointerException{        
        while (fileScanner1.hasNext()){
            fileScanner1.nextLine();
            String name = fileScanner1.nextLine().trim();
            String sDescription = fileScanner1.nextLine().trim();
            String lDescription = fileScanner1.nextLine().trim().translateEscapes();
            rooms.put(name, new Room(name,sDescription, lDescription));
            String exits = fileScanner1.nextLine();
            String[] exitsArray = exits.split(", ");
            exitMap.put(name, exitsArray);
        }//while
        rooms.values().forEach(i -> {
            exitMap.entrySet().stream().filter(j -> (i.getName().equals(j.getKey()))).forEachOrdered(j -> {
                i.setExits(j.getValue());
            });
        });
    }//buildHouse
    
    public void findItems() throws NullPointerException{
        String name, sDescription, lDescription, itemRooms, status;
        boolean takeable;
        
        while(fileScanner2.hasNext()){
            fileScanner2.nextLine();
            name = fileScanner2.nextLine().trim();
            sDescription = fileScanner2.nextLine().trim();
            lDescription = fileScanner2.nextLine().trim().translateEscapes();

            items.put(name, new Item(name, sDescription, lDescription));

            itemRooms = fileScanner2.nextLine().trim();
            Room[] roomsArray = getRooms(itemRooms.split(", "));
            this.itemRooms.put(name, roomsArray);
            status = fileScanner2.nextLine().trim();
            String[] statusArray = status.split(", ");
            fileScanner2.nextLine();
            takeable = fileScanner2.nextBoolean();
            this.status.put(name, statusArray);
            items.get(name).setTakeable(takeable);
            items.get(name).setItemRoom(roomsArray);
            items.get(name).setStatus(statusArray);
        }//while
        /*for (var i : items.values()){
            i.setItemRoom(this.itemRooms.get(i.getName()));
            i.setStatus(this.status.get(i.getName()));
        }//for*/
    }//findItems
}//RoomManager
