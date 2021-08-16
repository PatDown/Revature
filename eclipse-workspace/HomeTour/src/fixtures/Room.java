package fixtures;

import game.Player;

/**
 *
 * @author Pat Down
 */
public class Room extends Fixture {
    private Room[] exits;
    public Room(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
        this.exits = new Room[4];
    }//constructor
    
    public Room[] getExits(){
        return exits;
    }//getExits
    
    public void setExits(Room[] exits){
        this.exits = exits;
    }//setExits
    
    /*public Room getExit(Player player, String direction){
        player.look(this, direction);
    }//getExit*/
    
    @Override
    public String toString(){
        StringBuilder roomString = new StringBuilder();
        roomString.append(Room.super.getName());
        roomString.append("\n\n");
        roomString.append(Room.super.getLongDescription());
        
        return roomString.toString();
    }//toString override
}//Room
