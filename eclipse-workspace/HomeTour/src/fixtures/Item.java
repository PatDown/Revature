package fixtures;

import game.Main;

/**
 *
 * @author Pat Down
 */
public class Item extends Fixture {
    private boolean usable;
    private Room[] itemRoom;
    private String[] status;
    private String currentStatus;
    private boolean takeable;
    
    
    public Item(String name, String shortDescription, String longDescription){
        super(name, shortDescription, longDescription);
        itemRoom = new Room[Main.ROOM_COUNT];
        status = new String[2];
        currentStatus = "";
        usable = false;
        takeable = false;
    }//Item(String name, String shortDescription, String longDescription)

    public Item(String name) {
        super(name);
        itemRoom = new Room[Main.ROOM_COUNT];
        status = new String[2];
        currentStatus = "";
        usable = false;
        takeable = false; 
    }//Item(String name)
    
    public boolean isUsable(Room room) {
        for (Room r : itemRoom)
            if (r == room)
                return true;
        return false;
    }//isUsable(Room room)

    public void setUsable(boolean usable) {
        this.usable = usable;
    }//setUsable(boolean usable)

    public Room[] getItemRoom() {
        return itemRoom;
    }//getItemRoom()

    public void setItemRoom(Room[] itemRoom) {
        this.itemRoom = itemRoom;
    }//setItemRoom(Room[] itemRoom
    
    public boolean inRoom(Room room){
        for (Room i : itemRoom)
            if (i == room)
                return true;
        return false;
    }//inRoom(Room room)
    
    public String[] getStatus() {
        return status;
    }//getActoins()
    
    public String getCurrentStatus(){
        return currentStatus;
    }//getCurrentStatus()

    public void setCurrentStatus(String currentStatus){
        this.currentStatus = currentStatus;
    }//setCurrentStatus(String currentStatus)
    
    public void setStatus(String[] status) {
        this.status = status;
        setCurrentStatus(status[0]);
    }//setStatus(String[] status)
    
    public void changeStatus(){
        if(getCurrentStatus().equals(status[0]))
            setCurrentStatus(status[1]);
        else
            setCurrentStatus(status[0]);
    }//changeStatus()

    public boolean isTakeable() {
        return takeable;
    }//isTakeable()

    public void setTakeable(boolean takeable) {
        this.takeable = takeable;
    }//setTakeable(boolean takeable)
    
    @Override
    public String toString(){
        StringBuilder itemString = new StringBuilder();
        itemString.append(getName());
        itemString.append(" - ");
        itemString.append(getShortDescription());
        itemString.append(" Status: ");
        itemString.append(getCurrentStatus());
        return itemString.toString();
    }//toString override
}//Item
