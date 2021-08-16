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
    private final Map<String, Room> housePlan = new HashMap<>();
    private Scanner fileScanner;
    private final String path = new File("src/game/Rooms.txt").getAbsolutePath();

    public Room getStartingRoom() {
        return startingRoom;
    }//getStartingRoom

    public void init() throws NullPointerException{
        try {
            fileScanner = new Scanner(new File(path));
        }catch(FileNotFoundException e){
            System.out.print(e.getMessage());
        }//catch
        Room foyer = new Room(
            fileScanner.nextLine(),
            fileScanner.nextLine(),
            fileScanner.nextLine().translateEscapes());
        this.rooms[0] = foyer;
        this.startingRoom = foyer;
        buildHouse();
    }//init//init
    
    public void buildHouse(){
        housePlan.put(startingRoom.getName(), startingRoom);
        String name;
        while (fileScanner.hasNext()){
            name = fileScanner.nextLine();
            housePlan.put(name, new Room(name, fileScanner.nextLine(), fileScanner.nextLine().translateEscapes()));
        }//while
    }//buildHouse
}//RoomManager
