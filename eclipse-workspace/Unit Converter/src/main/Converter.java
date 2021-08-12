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
            switch (menuSelection) {
                case 1: // Distance
                    distanceConverter();
                    break;
                case 2: // Volume
                    volumeConverter();
                    break;
                case 3: // Temperature
                    temperatureConverter();
                    break;
                case 4: // Weight
                    weightConverter();
                    break;
                case 5: // Time
                    timeConverter();
                    break;
                case 6: // Quit
                    break;
                default:
                    System.out.println("Invalid input detected. Please enter a valid number from the list above.");
                    break;
            }// switch
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

    public static void distanceConverter() {
        int[] selectedUnits = unitSelection(distanceUnits);
        String unit1 = distanceUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = distanceUnits.get(selectedUnits[1]).getAbbr();
        System.out.println("Enter the quantity: ");
        float quantity = input.nextFloat(), converted;
        if (selectedUnits[0] == selectedUnits[1]) {
            System.out.println(quantity + unit1 + " = " + quantity + unit2);
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
            displayConversion(quantity, unit1, converted, unit2);
        } // else
    }// distanceConverter

    public static void volumeConverter() {
        int[] selectedUnits = unitSelection(volumeUnits);
        String unit1 = volumeUnits.get(selectedUnits[0]).getAbbr(), unit2 = volumeUnits.get(selectedUnits[1]).getAbbr();
        System.out.println("Enter the quantity: ");
        float quantity = input.nextFloat(), converted = 0;
        if (selectedUnits[0] == selectedUnits[1]) {
            System.out.println(quantity + unit1 + " = " + quantity + unit2);
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
            displayConversion(quantity, unit1, converted, unit2);
        } // else
        System.out.println("Method under construction.");
    }// volumeConverter

    public static void temperatureConverter() {
        int[] selectedUnits = unitSelection(temperatureUnits);
        String unit1 = temperatureUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = temperatureUnits.get(selectedUnits[1]).getAbbr();
        System.out.println("Enter the quantity: ");
        float quantity = input.nextFloat(), converted;

        if (unit1.equals(unit2)) {
            System.out.println(quantity + unit1 + " = " + quantity + unit2);
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
            displayConversion(quantity, unit1, converted, unit2);
        } // else
    }// temperatureConverter

    public static void weightConverter() {
        int[] selectedUnits = unitSelection(weightUnits);
        String unit1 = weightUnits.get(selectedUnits[0]).getAbbr(), unit2 = weightUnits.get(selectedUnits[1]).getAbbr();
        System.out.println("Enter the quantity: ");
        float quantity = input.nextFloat(), converted = 0;
        if (selectedUnits[0] == selectedUnits[1]) {
            System.out.println(quantity + unit1 + " = " + quantity + unit2);
        } else {
            switch (unit1) {
                case "g":
                    break;
                case "kg":
                    break;
                case "mg":
                    break;
                case "oz":
                    break;
                case "lb":
                    break;
                case "st":
                    break;
                default:// case "T"
                    break;
            }// switch
            displayConversion(quantity, unit1, converted, unit2);
        } // else
        System.out.println("Method under construction.");
    }// weightConverter

    public static void timeConverter() {
        int[] selectedUnits = unitSelection(timeUnits);
        String unit1 = timeUnits.get(selectedUnits[0]).getAbbr(), unit2 = timeUnits.get(selectedUnits[1]).getAbbr();
        System.out.println("Enter the quantity:");
        float quantity = input.nextFloat(), converted;
        if (selectedUnits[0] == selectedUnits[1]) {
            System.out.println(quantity + unit1 + " = " + quantity + unit2);
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
            displayConversion(quantity, unit1, converted, unit2);
        } // else
    }// timeConverter

    public static void displayConversion(float quantity1, String unit1, float quantity2, String unit2) {
        System.out.println(quantity1 + unit1 + " = " + quantity2 + unit2);
    }// displayConversion

    public static void converterTest(Map<Integer, Measurement> units) {

    }//converterTest
}// Converter
