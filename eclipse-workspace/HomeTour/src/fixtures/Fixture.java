package fixtures;

public abstract class Fixture {
    private String name;
    private String shortDescription;
    private String longDescription;

    public Fixture(String name, String shortDescription, String longDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }//constructor

    public String getName() {
        return name;
    }//getName

    public String getShortDescription() {
        return shortDescription;
    }//getShortDescription

    public String getLongDescription() {
        return longDescription;
    }//getLongDescription
    
    public void setLongDescription(String longDescription){
        this.longDescription = longDescription;
    }//setLongDescription
}//Fixture
