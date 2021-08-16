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
    private Map<String, Item> inventory ;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }//constructor
    
    public Room getCurrentRoom() {
        return currentRoom;
    }//getCurrentRoom
    
    public Map<String, Item> getInventory(){
        return inventory;
    }//getInventory
    
    public void go(String direction){
        switch (direction){
            
        }//switch
    }//go
    
    public void open(){
        
    }//open
    
    public void look(Room nextRoom){
        
    }//look
    
    public void take(String item){
        
    }//take
    
    public void place(String item){
        
    }//place
    
    public void use(String item){
        
    }//use
}//Player
