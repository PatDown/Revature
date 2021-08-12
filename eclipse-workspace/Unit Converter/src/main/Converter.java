package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Pat Down
 */
public class Converter {

    public static class Measurement {

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
    }// Measurement
    public static int QUIT = 6;
    public static Scanner input = new Scanner(System.in);
    public static Map<Integer, Measurement> distanceUnits = new HashMap<Integer, Measurement>();
    public static Map<Integer, Measurement> volumeUnits = new HashMap<Integer, Measurement>();
    public static Map<Integer, Measurement> temperatureUnits = new HashMap<Integer, Measurement>();
    public static Map<Integer, Measurement> weightUnits = new HashMap<Integer, Measurement>();
    public static Map<Integer, Measurement> timeUnits = new HashMap<Integer, Measurement>();

    public static void main(String[] args) {
        int menuSelection = 0;
        unitMapFiller();
        while (menuSelection != QUIT) {
            System.out.println("========================================");
            System.out.println("Please select an option:\n" + "1. Distance\n" + "2. Volume\n" + "3. Temperature\n"
                    + "4. Weight\n" + "5. Time\n" + "6. Quit");
            menuSelection = input.nextInt();
            converterTest(menuSelection);
            /*switch (menuSelection) {
                case 1: // Distance
                    distanceConverter(unitSelection(distanceUnits));
                    break;
                case 2: // Volume
                    volumeConverter(unitSelection(volumeUnits));
                    break;
                case 3: // Temperature
                    temperatureConverter(unitSelection(temperatureUnits));
                    break;
                case 4: // Weight
                    weightConverter(unitSelection(weightUnits));
                    break;
                case 5: // Time
                    timeConverter(unitSelection(timeUnits));
                    break;
                case 6: // Quit
                    break;
                default:
                    System.out.println("Invalid input detected. Please enter a valid number from the list above.");
                    break;
            }// switch*/
        } // while
    }// main

    public static void unitMapFiller() {
        // Distance
        int unitCounter = 1;
        distanceUnits.put(unitCounter++, new Measurement("cm", "Centimeter"));
        distanceUnits.put(unitCounter++, new Measurement("ft", "Foot"));
        distanceUnits.put(unitCounter++, new Measurement("in", "Inch"));
        distanceUnits.put(unitCounter++, new Measurement("km", "Kilometer"));
        distanceUnits.put(unitCounter++, new Measurement("m", "Meter"));
        distanceUnits.put(unitCounter++, new Measurement("mi", "Mile"));
        distanceUnits.put(unitCounter++, new Measurement("mm", "Millimeter"));
        distanceUnits.put(unitCounter++, new Measurement("yd", "Yard"));

        // Volume
        unitCounter = 1;
        volumeUnits.put(unitCounter++, new Measurement("cm3", "Cubic Centimeter"));
        volumeUnits.put(unitCounter++, new Measurement("ft3", "Cubic Foot"));
        volumeUnits.put(unitCounter++, new Measurement("in3", "Cubic Inch"));
        volumeUnits.put(unitCounter++, new Measurement("m3", "Cubic Meter"));
        volumeUnits.put(unitCounter++, new Measurement("c", "Cup"));
        volumeUnits.put(unitCounter++, new Measurement("gal", "Gallon(US)"));
        volumeUnits.put(unitCounter++, new Measurement("L", "Liter"));
        volumeUnits.put(unitCounter++, new Measurement("fl oz US", "Fluid Ounce (US)"));
        volumeUnits.put(unitCounter++, new Measurement("pt US", "Pint(US)"));
        volumeUnits.put(unitCounter++, new Measurement("qt US", "Quart (US)"));
        volumeUnits.put(unitCounter++, new Measurement("tbsp US", "Tablespoon (US)"));
        volumeUnits.put(unitCounter++, new Measurement("tsp US", "Teaspoon (US)"));

        // Temperature
        unitCounter = 1;
        temperatureUnits.put(unitCounter++, new Measurement("C", "Celcius"));
        temperatureUnits.put(unitCounter++, new Measurement("F", "Fahrenheit"));
        temperatureUnits.put(unitCounter++, new Measurement("K", "Kelvin"));

        // Weight
        unitCounter = 1;
        weightUnits.put(unitCounter++, new Measurement("g", "Gram"));
        weightUnits.put(unitCounter++, new Measurement("kg", "Kilogram"));
        weightUnits.put(unitCounter++, new Measurement("mg", "Milligram"));
        weightUnits.put(unitCounter++, new Measurement("oz", "Ounce"));
        weightUnits.put(unitCounter++, new Measurement("lb", "Pound"));
        weightUnits.put(unitCounter++, new Measurement("st", "Stone"));
        weightUnits.put(unitCounter++, new Measurement("T", "Ton"));

        // Time
        unitCounter = 1;
        timeUnits.put(unitCounter++, new Measurement("d", "Day"));
        timeUnits.put(unitCounter++, new Measurement("hr", "Hour"));
        timeUnits.put(unitCounter++, new Measurement("min", "Minute"));
        timeUnits.put(unitCounter++, new Measurement("mth", "Month (Average)"));
        timeUnits.put(unitCounter++, new Measurement("sec", "Second"));
        timeUnits.put(unitCounter++, new Measurement("wk", "Week"));
        timeUnits.put(unitCounter++, new Measurement("yr", "Year (Average)"));
    }// unitMapFiller

    public static int[] unitSelection(Map<Integer, Measurement> units) {
        int[] selectedUnits = new int[2];
        System.out.println("========================================");
        System.out.println("Units");
        units.entrySet().forEach(unit -> {
            System.out.println(unit.getKey() + ". " + unit.getValue().getName());
        }); // for

        System.out.println("Please select the first unit:");
        selectedUnits[0] = input.nextInt();
        while (selectedUnits[0] < 1 || selectedUnits[0] > units.size()) {
            System.out.println("Invalid input detected. Please enter a valid number from the list above.");
            selectedUnits[0] = input.nextInt();
        } // while

        System.out.println("Please select the second unit:");
        selectedUnits[1] = input.nextInt();
        while (selectedUnits[1] < 1 || selectedUnits[1] > units.size()) {
            System.out.println("Invalid input detected. Please enter a valid number from the list above.");
            selectedUnits[1] = input.nextInt();
        } // while
        
        return selectedUnits;
    }// unitSelection

    public static void distanceConverter(int[] selectedUnits, float quantity) {
        String unit1 = distanceUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = distanceUnits.get(selectedUnits[1]).getAbbr();
        //System.out.println("Enter the quantity: ");
        float /*quantity = input.nextFloat(),*/ converted;
        if (selectedUnits[0] == selectedUnits[1]) {
            converted = quantity;
        } else {
            switch (unit1) {
                case "cm":
                    switch (unit2) {
                        case "m":
                            converted = quantity / 100;
                            break;
                        case "km":
                            converted = quantity / 100000;
                            break;
                        case "mm":
                            converted = quantity * 10;
                            break;
                        default:
                            converted = (float) (quantity / 2.54);
                            if (!unit2.equals("in")) {
                                converted /= 12;
                                switch (unit2) {
                                    case "yd":
                                        converted /= 3;
                                        break;
                                    case "mi":
                                        converted /= 5280;
                                        break;
                                    default://unit2 = "ft"
                                        break;
                                }// switch
                            } // if
                            break;
                    }// switch
                    break;
                case "ft":
                    switch (unit2) {
                        case "yd":
                            converted = quantity / 3;
                            break;
                        case "mi":
                            converted = quantity / 5280;
                            break;
                        default:
                            converted = quantity * 12;
                            if (!unit2.equals("in")) {
                                converted *= (float) 2.54;
                                switch (unit2) {
                                    case "mm":
                                        converted *= 19;
                                        break;
                                    case "m":
                                        converted /= 100;
                                        break;
                                    case "km":
                                        converted /= 100000;
                                        break;
                                    default://unit2 = "cm"
                                        break;
                                }//switch
                            }//if
                            break; //else
                    }//switch
                    break;
                case "in":
                    if (unit2.equals("ft") || unit2.equals("mi") || unit2.equals("yd")) {
                        converted = quantity / 12;
                        if (unit2.equals("yd")) {
                            converted /= 3;
                        }
                        if (unit2.equals("mi")) {
                            converted /= 5280;
                        }
                    }//if
                    else {
                        converted = quantity * (float) 2.54;
                        switch (unit2) {
                            case "mm":
                                converted *= 10;
                                break;
                            case "m":
                                converted /= 1000;
                                break;
                            case "km":
                                converted /= 100000;
                                break;
                            default://unit2 = "cm"
                                break;
                        }//switch
                    }//else
                    break;
                case "km":
                    converted = quantity * 1000;
                    if (!unit2.equals("m")) {
                        if (unit2.equals("mm")) {
                            converted *= 1000;
                        } else {
                            converted *= 100;
                            if (!unit2.equals("cm")) {
                                converted /= (float) 2.54;
                                if (!unit2.equals("in")) {
                                    converted /= 12;
                                    switch (unit2) {
                                        case "yd":
                                            converted /= 3;
                                            break;
                                        case "mi":
                                            converted /= 5280;
                                            break;
                                        default:// unit2 = "ft"
                                            break;
                                    }// switch
                                } // if 
                            }//if
                        }//else
                    }//if
                    break;
                case "m":
                    switch (unit2) {
                        case "km":
                            converted = quantity / 1000;
                            break;
                        case "mm":
                            converted = quantity * 1000;
                            break;
                        default:
                            converted = quantity * 100;
                            if (!unit2.equals("cm")) {
                                converted /= (float) 2.54;
                                if (!unit2.equals("in")) {
                                    converted /= 12;
                                    switch (unit2) {
                                        case "yd":
                                            converted /= 3;
                                            break;
                                        case "mi":
                                            converted /= 5280;
                                            break;
                                        default://unit2 = "ft"
                                            break;
                                    }// switch
                                } // if 
                            }//if
                            break;
                    }//switch
                    break;
                case "mi":
                    switch (unit2) {
                        case "yd":
                            converted = quantity * 1760;
                            break;
                        case "ft":
                            converted = quantity * 5280;
                            break;
                        default:
                            converted = quantity * 63360;
                            if (!unit2.equals("in")) {
                                converted *= (float) 2.54;
                                switch (unit2) {
                                    case "mm":
                                        converted *= 10;
                                        break;
                                    case "m":
                                        converted /= 1000;
                                        break;
                                    case "km":
                                        converted /= 100000;
                                        break;
                                    default://unit2 = "cm"
                                        break;
                                }//switch
                            }//if
                            break;
                    }//switch
                    break;
                case "mm":
                    converted = quantity * 10;
                    if (!unit2.equals("cm")) {
                        switch (unit2) {
                            case "m":
                                converted /= 100;
                                break;
                            case "km":
                                converted /= 100000;
                                break;
                            default:
                                converted /= (float) 2.54;
                                if (!unit2.equals("in")) {
                                    converted /= 12;
                                    switch (unit2) {
                                        case "yd":
                                            converted /= 3;
                                            break;
                                        case "mi":
                                            converted /= 5280;
                                            break;
                                        default:// unit2 = "ft"
                                            break;
                                    }// switch
                                } // if
                                break;
                        }// switch
                    } // if
                    break;
                default: // unit1 = "yd"
                    switch (unit2) {
                        case "mi":
                            converted = quantity / 1760;
                            break;
                        case "ft":
                            converted = quantity * 3;
                            break;
                        default:
                            converted = quantity * 36;
                            if (!unit2.equals("in")) {
                                converted *= (float) 2.54;
                                switch (unit2) {
                                    case "mm":
                                        converted *= 10;
                                        break;
                                    case "m":
                                        converted /= 1000;
                                        break;
                                    case "km":
                                        converted /= 100000;
                                        break;
                                    default://unit2 = "cm"
                                        break;
                                }//switch
                            }//if
                            break;
                    }//switch
                    break;
            }// switch
        } // else
        displayConversion(quantity, unit1, converted, unit2);
    }// distanceConverter

    public static void volumeConverter(int[] selectedUnits, float quantity) {
        String unit1 = volumeUnits.get(selectedUnits[0]).getAbbr(), unit2 = volumeUnits.get(selectedUnits[1]).getAbbr();
        //System.out.println("Enter the quantity: ");
        float /*quantity = input.nextFloat(),*/ converted = 0;
        if (selectedUnits[0] == selectedUnits[1]) {
            converted = quantity;
        } else {
            switch (unit1) {
                case "cm3":
                    break;
                case "ft3":
                    break;
                case "in3":
                    break;
                case "m3":
                    break;
                case "c":
                    break;
                case "gal":
                    break;
                case "L":
                    break;
                case "oz":
                    break;
                case "pt":
                    break;
                case "qt":
                    break;
                case "tbsp":
                    break;
                default:// case "tsp"
                    break;
            }// switch
        } // else
        //displayConversion(quantity, unit1, converted, unit2);
        System.out.println("Method under construction.");
    }// volumeConverter

    public static void temperatureConverter(int[] selectedUnits, float quantity) {
        String unit1 = temperatureUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = temperatureUnits.get(selectedUnits[1]).getAbbr();
        //System.out.println("Enter the quantity: ");
        float /*quantity = input.nextFloat(),*/ converted;

        if (unit1.equals(unit2)) {
            converted = quantity;
        } else {
            switch (unit1) {
                case "F":
                    converted = (quantity - 32) * 5 / 9;
                    if (unit2.equals("K")) {
                        converted += 273.15;
                    }
                    break;
                case "C":
                    if (unit2.equals("F")) {
                        converted = (quantity * 9 / 5) + 32;
                    } else {
                        converted = (float) (quantity + 273.15);
                    }
                    break;
                default:// case "K"
                    converted = (float) (quantity - 273.15);
                    if (unit2.equals("F")) {
                        converted = (converted * 9 / 5) + 32;
                    }
                    break;
            }// switch
        } // else
        displayConversion(quantity, unit1, converted, unit2);
    }// temperatureConverter

    public static void weightConverter(int[] selectedUnits, float quantity) {
        String unit1 = weightUnits.get(selectedUnits[0]).getAbbr(), unit2 = weightUnits.get(selectedUnits[1]).getAbbr();
        //System.out.println("Enter the quantity: ");
        float /*quantity = input.nextFloat(),*/ converted = 0;
        if (selectedUnits[0] == selectedUnits[1]) {
            converted = quantity;
        } else {
            switch (unit1) {
                case "g":
                    if (unit2.equals("mg"))
                        converted = quantity * 1000;
                    else {
                        converted = quantity / 1000;
                        if (!unit2.equals("kg")) {
                            converted *= (float) 35.274;
                            switch (unit2){
                                case "lb":
                                    converted /= 16;
                                    break;
                                case "st":
                                    converted /= 224;
                                    break;
                                case "T":
                                    converted /= 32000;
                                    break;
                                default://unit2 = "oz"
                                    break;
                            }//switch
                        }//if
                    }//else
                    break;
                case "kg":
                    switch(unit2) {
                        case "g":
                            converted = quantity * 1000;
                            break;
                        case "mg":
                            converted = quantity * 1000000;
                            break;
                        default:
                            converted = (float) (quantity * 35.274);
                            switch (unit2){
                                case "lb":
                                    converted /= 16;
                                    break;
                                case "st":
                                    converted /= 224;
                                    break;
                                case "T":
                                    converted /= 32000;
                                    break;
                                default://unit2 = "oz"
                                    break;
                            }//switch
                            break;
                    }//switch
                    break;
                case "mg":
                    converted = quantity / 1000;
                    if (!unit2.equals("g")){
                        converted /= 1000;
                        if (!unit2.equals("kg")) {
                            converted *= (float) 35.274;
                            switch (unit2){
                                case "lb":
                                    converted /= 16;
                                    break;
                                case "st":
                                    converted /= 224;
                                    break;
                                case "T":
                                    converted /= 32000;
                                    break;
                                default://unit2 = "oz"
                                    break;
                            }//switch
                        }//if
                    }//if
                    break;
                case "oz":
                    switch(unit2){
                        case "lb":
                            converted = quantity / 16;
                            break;
                        case "st":
                            converted = quantity / 224;
                            break;
                        case "T":
                            converted = quantity / 32000;
                            break;
                        default:
                            converted = (float)(quantity / 35.274);
                            if (!unit2.equals("kg")){
                                converted *= 1000;
                                if (!unit2.equals("g"))
                                    converted *= 1000;
                            }//if
                            break;
                    }//switch
                    break;
                case "lb":
                    switch (unit2){
                        case "st":
                            converted = quantity / 14;
                            break;
                        case "T":
                            converted = quantity / 2000;
                            break;
                        default:
                            converted = quantity * 16;
                            if (!unit2.equals("oz")){
                                converted /= (float)35.274;
                                if (!unit2.equals("kg")){
                                    converted *= 1000;
                                    if (!unit2.equals("g"))
                                        converted *= 1000;
                                }//if
                            }//if
                            break;
                    }//switch
                    break;
                case "st":
                    if (unit2.equals("lb") || unit2.equals("T")){
                        converted = quantity * 14;
                        if (unit2.equals("T"))
                            converted /= 2000;
                    }else{
                        converted *= 224;
                        if (!unit2.equals("oz")){
                            converted /= (float) 35.274;
                            if (unit2.equals("g"))
                                converted *= 1000;
                            if (unit2.equals("mg"))
                                converted *= 1000000;
                        }//if
                    }//else
                    break;
                default:// case "T"
                    converted = quantity * 2000;
                    if (unit2.equals("st"))
                        converted /= 14;
                    if (!unit2.equals("st") && !unit2.equals("lb")) {
                        converted *= 16;
                        if (!unit2.equals("oz")){
                            converted /= (float) 35.274;
                            if (unit2.equals("g"))
                                converted *= 1000;
                            if (unit2.equals("mg"))
                                converted *= 1000000;
                        }//if
                    }//if
                    break;
            }// switch
        } // else
        displayConversion(quantity, unit1, converted, unit2);
    }// weightConverter

    public static void timeConverter(int[] selectedUnits, float quantity) {
        String unit1 = timeUnits.get(selectedUnits[0]).getAbbr(), unit2 = timeUnits.get(selectedUnits[1]).getAbbr();
        //System.out.println("Enter the quantity:");
        float /*quantity = input.nextFloat(),*/ converted;
        if (selectedUnits[0] == selectedUnits[1]) {
            converted = quantity;
        } else {
            switch (unit1) {
                case "d":
                    switch (unit2) {
                        case "sec":
                            converted = quantity * 86400;
                            break;
                        case "min":
                            converted = quantity * 1440;
                            break;
                        case "hr":
                            converted = quantity * 24;
                            break;
                        case "wk":
                            converted = quantity / 7;
                            break;
                        case "mth":
                            converted = quantity / 30;
                            break;
                        default:// case "yr"
                            converted = quantity / 365;
                            break;
                    }// switch
                    break;
                case "hr":
                    switch (unit2) {
                        case "sec":
                            converted = quantity * 3600;
                            break;
                        case "min":
                            converted = quantity * 60;
                            break;
                        default:
                            converted = quantity / 24;
                            switch (unit2) {
                                case "wk":
                                    converted /= 7;
                                    break;
                                case "mth":
                                    converted /= 30;
                                    break;
                                case "yr":
                                    converted /= 365;
                                    break;
                                default:// Days
                                    break;
                            }// switch
                            break;
                    }// switch
                    break;
                case "min":
                    if (unit2.equals("sec")) {
                        converted = quantity * 60;
                    } else {
                        converted = quantity / 60;
                        if (!unit2.equals("hr")) {
                            converted /= 24;
                            switch (unit2) {
                                case "wk":
                                    converted /= 7;
                                    break;
                                case "mth":
                                    converted /= 30;
                                    break;
                                case "yr":
                                    converted /= 365;
                                    break;
                                default:// Days
                                    break;
                            }// switch
                        } // if
                    } // else
                    break;
                case "mth":
                    if (unit2.equals("yr")) {
                        converted = quantity / 12;
                    } else {
                        converted = quantity * 4;
                        if (!unit2.equals("wk")) {
                            converted *= 7;
                            if (!unit2.equals("d")) {
                                converted *= 24;
                                if (!unit2.equals("hr")) {
                                    converted *= 60;
                                    if (!unit2.equals("min")) {
                                        converted *= 60;
                                    }
                                } // if
                            } // if
                        } // if
                    } // else
                    break;
                case "sec":
                    converted = quantity * 60;
                    if (!unit2.equals("min")) {
                        converted /= 60;
                        if (!unit2.equals("hr")) {
                            converted /= 24;
                            switch (unit2) {
                                case "wk":
                                    converted /= 7;
                                    break;
                                case "mth":
                                    converted /= 30;
                                    break;
                                case "yr":
                                    converted /= 365;
                                    break;
                                default:// Days
                                    break;
                            }// switch
                        } // if
                    } // if
                    break;
                case "wk":
                    switch (unit2) {
                        case "mth":
                            converted = quantity / 4;
                            break;
                        case "yr":
                            converted = quantity / 52;
                            break;
                        default:
                            converted = quantity * 7;
                            if (!unit2.equals("d")) {
                                converted *= 24;
                                if (!unit2.equals("hr")) {
                                    converted *= 60;
                                    if (!unit2.equals("min")) {
                                        converted *= 60;
                                    }
                                } // if
                            } // if
                            break;
                    }// switch
                    break;
                default:// case "yr"
                    switch (unit2) {
                        case "mth":
                            converted = quantity * 12;
                            break;
                        case "wk":
                            converted = quantity * 52;
                            break;
                        default:
                            converted = quantity * 365;
                            if (!unit2.equals("d")) {
                                converted *= 24;
                                if (!unit2.equals("hr")) {
                                    converted *= 60;
                                    if (!unit2.equals("min")) {
                                        converted *= 60;
                                    }
                                } // if
                            } // if
                            break;
                    }// switch
                    break;
            }// switch
        } // else
        displayConversion(quantity, unit1, converted, unit2);
    }// timeConverter

    public static void displayConversion(float quantity1, String unit1, float quantity2, String unit2) {
        System.out.printf("%f%s  =  %f%s", quantity1, unit1, quantity2, unit2);
    }// displayConversion

    public static void converterTest(int menuSelection) {
        
        switch (menuSelection) {
                case 1: // Distance
                    for(int i = 1; i <= distanceUnits.size(); i++, System.out.print("\n")){
                        System.out.println(distanceUnits.get(i).getName());
                        for(int j = 1; j <= distanceUnits.size(); j++){
                            int[] selectedUnits = {i, j};
                            distanceConverter(selectedUnits, 1);
                            if (j % 2 == 0)
                                System.out.print("\n");
                            else
                                System.out.print("\t\t");
                        }//for
                    }//for
                    break;
                case 2: // Volume
                    for(int i = 1; i <= volumeUnits.size(); i++, System.out.print("\n")){
                        System.out.println(volumeUnits.get(i).getName());
                        for(int j = 1; j <= volumeUnits.size(); j++){
                            int[] selectedUnits = {i, j};
                            volumeConverter(selectedUnits, 1);
                            if (j % 2 == 0)
                                System.out.print("\n");
                            else
                                System.out.print("\t\t");
                        }//for
                    }//for
                    break;
                case 3: // Temperature
                    for(int i = 1; i <= temperatureUnits.size(); i++, System.out.print("\n")){
                        System.out.println(temperatureUnits.get(i).getName());
                        for(int j = 1; j <= temperatureUnits.size(); j++){
                            int[] selectedUnits = {i, j};
                            temperatureConverter(selectedUnits, 0);
                            if (j % 2 == 0)
                                System.out.print("\n");
                            else
                                System.out.print("\t\t");
                        }//for
                    }//for
                    break;
                case 4: // Weight
                    for(int i = 1; i <= weightUnits.size(); i++, System.out.print("\n")){
                        System.out.println(weightUnits.get(i).getName());
                        for(int j = 1; j <= weightUnits.size(); j++){
                            int[] selectedUnits = {i, j};
                            weightConverter(selectedUnits, 1);
                            if (j % 2 == 0)
                                System.out.print("\n");
                            else
                                System.out.print("\t\t");
                        }//for
                    }//for
                    break;
                case 5: // Time
                    for(int i = 1; i <= timeUnits.size(); i++, System.out.print("\n")){
                        System.out.println(timeUnits.get(i).getName());
                        for(int j = 1; j <= timeUnits.size(); j++){
                            int[] selectedUnits = {i, j};
                            timeConverter(selectedUnits, 1);
                            if (j % 2 == 0)
                                System.out.print("\n");
                            else
                                System.out.print("\t\t");
                        }//for
                    }//for
                    break;
                case 6: // Quit
                    break;
                default:
                    System.out.println("Invalid input detected. Please enter a valid number from the list above.");
                    break;
            }// switch
    }//converterTest
}// Converter
