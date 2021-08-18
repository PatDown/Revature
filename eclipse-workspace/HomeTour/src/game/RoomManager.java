package game;

import fixtures.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Pat Down
 */
public class RoomManager {
    private static Room startingRoom;
    private static final Map<String, Room> rooms = new HashMap<>();
    private static final Map<String, String[]> exitMap = new HashMap<>();
    private Scanner fileScanner;
    private final String path = new File("src/game/Rooms.txt").getAbsolutePath();

    public Room getStartingRoom() {
        return startingRoom;
    }//getStartingRoom
    
    public Map<String, String[]> getExitMap(){
        return exitMap;
    }//getExitMap
    
    public Map<String, Room> getRooms(){
        return rooms;
    }//getRooms
    
    public void init() throws NullPointerException{
        try {
            fileScanner = new Scanner(new File(path));
        }catch(FileNotFoundException e){
            System.out.print(e.getMessage());
        }//catch
        Room foyer = new Room(
            fileScanner.nextLine().trim(),
            fileScanner.nextLine().trim(),
            fileScanner.nextLine().trim().translateEscapes());
        String exits = fileScanner.nextLine();
        Scanner s = new Scanner(exits);
        s.useDelimiter(",");
        String[] exitsArray = new String[s.nextInt()*2];
        int exitNum = 0;
        while (s.hasNext()){
            exitsArray[exitNum++] = s.next().trim();
        }//while
        startingRoom = foyer;
        rooms.put(foyer.getName(), foyer);
        exitMap.put(foyer.getName(), exitsArray);
        buildHouse();
        for (var i : rooms.values()){
            for (var j : exitMap.entrySet()){
                if (i.getName().equals(j.getKey()))
                    i.setExits(j.getValue());
            }//for
        }//for
        fileScanner.close();
    }//init
    
    public void buildHouse() throws NullPointerException{        
        while (fileScanner.hasNext()){
            fileScanner.nextLine();
            String name = fileScanner.nextLine().trim();
            String sDescription = fileScanner.nextLine().trim();
            String lDescription = fileScanner.nextLine().trim().translateEscapes();
            rooms.put(name, new Room(name,sDescription, lDescription));
            String exits = fileScanner.nextLine();
            Scanner s = new Scanner(exits);
            s.useDelimiter(",");
            String[] exitsArray = new String[s.nextInt()*2];
            int exitNum = 0;
            while (s.hasNext()){
                exitsArray[exitNum++] = s.next().trim();
            }//while
            exitMap.put(name, exitsArray);
        }//while
        
    }//buildHouse
}//RoomManager
