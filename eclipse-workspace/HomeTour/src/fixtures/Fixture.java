package fixtures;

public abstract class Fixture {
    private final String name;
    private final String shortDescription;
    private final String longDescription;

    public Fixture(String name, String shortDescription, String longDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }//Fixture(String, String, String)
    
    public Fixture(String name){
        this.name = name;
        shortDescription = "";
        longDescription = "";
    }//Fixture(String)
    
    public String getName() {
        return name;
    }//getName()

    public String getShortDescription() {
        return shortDescription;
    }//getShortDescription()

    public String getLongDescription() {
        return longDescription;
    }//getLongDescription()
}//Fixture
