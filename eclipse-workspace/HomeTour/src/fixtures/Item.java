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
    }//Item(String, String, String)

    public Item(String name) {
        super(name);
        itemRoom = new Room[Main.ROOM_COUNT];
        status = new String[2];
        currentStatus = "";
        usable = false;
        takeable = false; 
    }//Item(String)

    public Item(String name, String shortDescription, String longDescription, boolean usable, String[] status, boolean takeable) {
        super(name, shortDescription, longDescription);
        this.usable = usable;
        itemRoom = new Room[Main.ROOM_COUNT];
        this.status = status;
        currentStatus = status[0];
        this.takeable = takeable;
    }//Item(String, String, String, boolean, String[], boolean) 
    
    public boolean isUsable(){
        return usable;
    }//isUsable()
    
    public boolean isUsable(Room room) {
        for (Room r : itemRoom)
            if (r == room)
                setUsable(true);
        return isUsable();
    }//isUsable(Room)

    public void setUsable(boolean usable) {
        this.usable = usable;
    }//setUsable(boolean)

    public Room[] getItemRoom() {
        return itemRoom;
    }//getItemRoom()

    public void setItemRoom(Room[] itemRoom) {
        this.itemRoom = itemRoom;
    }//setItemRoom(Room[])
    
    public boolean inRoom(Room room){
        for (Room i : itemRoom)
            if (i == room)
                return true;
        return false;
    }//inRoom(Room)
    
    public String[] getStatus() {
        return status;
    }//getActoins()
    
    public String getCurrentStatus(){
        return currentStatus;
    }//getCurrentStatus()

    public void setCurrentStatus(String currentStatus){
        this.currentStatus = currentStatus;
    }//setCurrentStatus(String)
    
    public void setStatus(String[] status) {
        this.status = status;
        setCurrentStatus(status[0]);
    }//setStatus(String[])
    
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
    }//setTakeable(boolean)
    
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
