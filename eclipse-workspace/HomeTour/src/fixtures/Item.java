package fixtures;

/**
 *
 * @author Pat Down
 */
public class Item extends Fixture {
    private boolean usable;
    public Item(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
        usable = false;
    }//constructor

    public boolean isUsable() {
        return usable;
    }//isUsable

    public void setUsable(boolean usable) {
        this.usable = usable;
    }//setUsable
      
}//Item
