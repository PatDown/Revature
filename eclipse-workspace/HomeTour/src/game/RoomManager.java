package game;

import fixtures.Room;
import java.io.*;
import java.util.*;

/**
 *
 * @author Pat Down
 */
public class RoomManager {
    private Room startingRoom;
    private final Room[] rooms = new Room[16];
    public final Map<String, Room> housePlan = new HashMap<>();
    public Map<String, String[]> exitMap = new HashMap<>();
    private Scanner fileScanner;
    private final String path = new File("src/game/Rooms.txt").getAbsolutePath();

    public Room getStartingRoom() {
        return startingRoom;
    }//getStartingRoom

    public void init() throws NullPointerException{
        exitMap = new HashMap<>();
        try {
            fileScanner = new Scanner(new File(path));
        }catch(FileNotFoundException e){
            System.out.print(e.getMessage());
        }//catch
        Room foyer = new Room(
            fileScanner.nextLine().trim(),
            fileScanner.nextLine().trim(),
            fileScanner.nextLine().translateEscapes());
        String exits = fileScanner.nextLine();
        Scanner s = new Scanner(exits);
        s.useDelimiter(",");
        String[] exitsArray = new String[s.nextInt()*2];
        int exitNum = 0;
        while (s.hasNext()){
            exitsArray[exitNum++] = s.next().trim();
        }//while
        this.rooms[0] = foyer;
        this.startingRoom = foyer;
        this.housePlan.put(foyer.getName(), foyer);
        buildHouse();
        for (var e : housePlan.entrySet()){
            System.out.println(e.getKey());
            e.getValue().printExits();
        }//for
        fileScanner.close();
    }//init
    
    public void buildHouse() throws NullPointerException{
        String name, sDescription, lDescription;
        
        while (fileScanner.hasNext()){
            fileScanner.nextLine();
            name = fileScanner.nextLine().trim();
            sDescription = fileScanner.nextLine().trim();
            lDescription = fileScanner.nextLine().translateEscapes();
            this.housePlan.put(name, new Room(name,sDescription, lDescription));
            String exits = fileScanner.nextLine();
            Scanner s = new Scanner(exits);
            s.useDelimiter(",");
            String[] exitsArray = new String[s.nextInt()*2];
            int exitNum = 0;
            while (s.hasNext()){
                exitsArray[exitNum++] = s.next().trim();
            }//while
            this.exitMap.put(name, exitsArray);
        }//while
        for (var i : exitMap.entrySet()){
            this.housePlan.get(i.getKey()).setExits(i.getValue());
        }//for
    }//buildHouse
}//RoomManager
