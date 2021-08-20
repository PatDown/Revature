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
    private final Map<String, Item> items = new HashMap<>();
    private final Map<Item, Room[]> itemRooms = new HashMap<>();
    private final Map<String, String[]> status = new HashMap<>();
    private Scanner fileScanner1;
    private Scanner fileScanner2;
    private final String roomText = new File("resources/Rooms.txt").getAbsolutePath();
    private final String itemText = new File("resources/Items.txt").getAbsolutePath();

    public Room getStartingRoom() {
        return startingRoom;
    }//getStartingRoom()
    
    public Map<String, String[]> getExitMap(){
        return exitMap;
    }//getExitMap()
    
    public Map<String, Room> getRooms(){
        return rooms;
    }//getRooms()
    
    public Room[] getRooms(String[] rooms){
        Room[] roomArray = new Room[rooms.length];
        for (int i = 0; i < rooms.length; i++)
            roomArray[i] = this.rooms.get(rooms[i]);
        return roomArray;
    }//getRooms(String[])
    
    public Room getRoom(String name){
        return rooms.get(name);
    }//getRoom(String)

    public Map<String, Item> getItems() {
        return items;
    }//getItems()
    
    public Item getItem(String name){
        for (Item i : items.values())
            if (i.getName().equalsIgnoreCase(name))
                name = i.getName();
        return items.get(name);
    }//getItem(String)
    
    public boolean isItem(String name){
        return items.values().stream().anyMatch(i -> (i.getName().equalsIgnoreCase(name)));
    }//isItem(String)
    
    public void init() throws NullPointerException{
        try {
            fileScanner1 = new Scanner(new File(roomText));
        }catch(FileNotFoundException e){
            System.out.print(e.getMessage());
        }//catch(FileNotFoundException)
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
        }//catch (FileNotFoundException)
        Item tv = new Item(
            fileScanner2.nextLine().trim(), 
            fileScanner2.nextLine().trim(), 
            fileScanner2.nextLine().trim().translateEscapes());
        String iRooms = fileScanner2.nextLine().trim();
        Room[] roomsArray = getRooms(iRooms.split(", "));
        String statuses = fileScanner2.nextLine().trim();
        String[] statusArray = statuses.split(", ");
        String takeStatus = fileScanner2.nextLine();
        boolean takeable = takeStatus.equals("true");
        this.itemRooms.put(tv, roomsArray);
        this.status.put(tv.getName(), statusArray);
        tv.setTakeable(takeable);
        tv.setItemRoom(roomsArray);
        tv.setStatus(statusArray);
        items.put(tv.getName(), tv);
        findItems();
        
        rooms.values().forEach(i -> {
            items.values().stream().filter(j -> (j.inRoom(i))).forEachOrdered(j -> {
                i.addItem(j);
            });
        });
        fileScanner2.close();
    }//init()
    
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
        }//while (fileScanner1.hasNext())
        
        rooms.values().forEach(i -> {
            exitMap.entrySet().stream().filter(j -> (i.getName().equals(j.getKey()))).forEachOrdered(j -> {
                i.setExits(j.getValue());
            });
        });
    }//buildHouse()
    
    public void findItems() throws NullPointerException{        
        while(fileScanner2.hasNext()){
            fileScanner2.nextLine();
            String name = fileScanner2.nextLine().trim();
            String sDescription = fileScanner2.nextLine().trim();
            String lDescription = fileScanner2.nextLine().trim().translateEscapes();
            Item item = new Item(name, sDescription, lDescription);

            String iRooms = fileScanner2.nextLine().trim();
            Room[] roomsArray = getRooms(iRooms.split(", "));
            itemRooms.put(item, roomsArray);
            String statuses = fileScanner2.nextLine().trim();
            String[] statusArray = statuses.split(", ");
            String takeStatus = fileScanner2.nextLine();
            boolean takeable = takeStatus.equals("true");
            status.put(name, statusArray);
            item.setTakeable(takeable);
            item.setItemRoom(roomsArray);
            item.setStatus(statusArray);
            
            items.put(name, item);
        }//while(fileScanner2.hasNext())
        
        items.values().stream().map(i -> {
            i.setItemRoom(itemRooms.get(i));
            return i;
        }).forEachOrdered(i -> {
            i.setStatus(status.get(i.getName()));
        });
    }//findItems()
}//RoomManager
