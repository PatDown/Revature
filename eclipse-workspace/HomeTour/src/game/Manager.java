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
    private final Map<Item, Room[]> itemRooms = new HashMap<>();
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
        for (Item i : items.values()){
            if (i.getName().equalsIgnoreCase(name))
                name = i.getName();
        }//for
        return items.get(name);
    }//getItem
    
    public void setItemRooms(Item item, Room room){
        
    }//setItemRooms
    
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
        Item lights = new Item(
            fileScanner2.nextLine().trim(), 
            fileScanner2.nextLine().trim(), 
            fileScanner2.nextLine().trim().translateEscapes());
        String itemRooms = fileScanner2.nextLine().trim();
        Room[] roomsArray = getRooms(itemRooms.split(", "));
        String actions = fileScanner2.nextLine().trim();
        String[] statusArray = actions.split(", ");
        String takeStatus = fileScanner2.nextLine();
        boolean takeable = takeStatus.equals("true");
        items.put(lights.getName(), lights);
        items.get(lights.getName()).setTakeable(takeable);
        this.itemRooms.put(lights, roomsArray);
        this.status.put(lights.getName(), statusArray);
        items.get(lights.getName()).setItemRoom(roomsArray);
        items.get(lights.getName()).setStatus(statusArray);
        findItems();
        
        for (var i : rooms.values()){
            for (var j : items.values()){
                if(j.isInRoom(i))
                    i.setItems(j);
            }//for
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
        while(fileScanner2.hasNext()){
            fileScanner2.nextLine();
            String name = fileScanner2.nextLine().trim();
            String sDescription = fileScanner2.nextLine().trim();
            String lDescription = fileScanner2.nextLine().trim().translateEscapes();
            Item item = new Item(name, sDescription, lDescription);
            items.put(name, item);

            String iRooms = fileScanner2.nextLine().trim();
            Room[] roomsArray = getRooms(iRooms.split(", "));
            this.itemRooms.put(item, roomsArray);
            String status = fileScanner2.nextLine().trim();
            String[] statusArray = status.split(", ");
            String takeStatus = fileScanner2.nextLine();
            boolean takeable = takeStatus.equals("true");
            this.status.put(name, statusArray);
            items.get(name).setTakeable(takeable);
            items.get(name).setItemRoom(roomsArray);
            items.get(name).setStatus(statusArray);
        }//while
        for (var i : items.values()){
            i.setItemRoom(this.itemRooms.get(i));
            i.setStatus(this.status.get(i.getName()));
        }//for
    }//findItems
}//RoomManager
