package main;

/**
 *
 * @author Pat Down
 */
public class Measurement {
    public String abbr;
    public String name;

    public Measurement(String abbr, String name) {
        this.abbr = abbr;
        this.name = name;
    }// default constructor

    public String getAbbr() {
        return abbr;
    }// getAbbr

    public String getName() {
        return name;
    }// getName
}//Measurement
