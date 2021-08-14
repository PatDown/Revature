package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Pat Down
 */
public class Converter {

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
            System.out.println("\n========================================");
            System.out.println("Please select an option:\n" + "1. Distance\n" + "2. Volume\n" + "3. Temperature\n"
                    + "4. Weight\n" + "5. Time\n" + "6. Quit");
            menuSelection = input.nextInt();
            //converterTest(menuSelection);
            switch (menuSelection) {
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
            }// switch
        } // while
        input.close();
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
        volumeUnits.put(unitCounter++, new Measurement("ft3", "Cubic Foot"));
        volumeUnits.put(unitCounter++, new Measurement("in3", "Cubic Inch"));
        volumeUnits.put(unitCounter++, new Measurement("m3", "Cubic Meter"));
        volumeUnits.put(unitCounter++, new Measurement("c", "Cup (US)"));
        volumeUnits.put(unitCounter++, new Measurement("fl oz", "Fluid Ounce (US)"));
        volumeUnits.put(unitCounter++, new Measurement("gal", "Gallon(US)"));
        volumeUnits.put(unitCounter++, new Measurement("L", "Liter"));
        volumeUnits.put(unitCounter++, new Measurement("mL", "Milliliter"));
        volumeUnits.put(unitCounter++, new Measurement("pt", "Pint(US)"));
        volumeUnits.put(unitCounter++, new Measurement("qt", "Quart (US)"));
        volumeUnits.put(unitCounter++, new Measurement("tbsp", "Tablespoon (US)"));
        volumeUnits.put(unitCounter++, new Measurement("tsp", "Teaspoon (US)"));

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

    public static void distanceConverter(int[] selectedUnits/*, double quantity*/) {
        String unit1 = distanceUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = distanceUnits.get(selectedUnits[1]).getAbbr(),
                temp = unit1;
        System.out.println("Enter the quantity: ");
        double quantity = input.nextFloat(), converted, mid;
        if (selectedUnits[0] == selectedUnits[1])
            mid = quantity;
        else {
            switch (unit1) {
                case "cm"://direct conversions: km, m, yd, ft, in, mm
                    switch (unit2) {
                        case "m":
                        case "km":
                        case "mm":
                        case "yd":
                        case "in":
                        case "ft":
                            mid = quantity;
                            break;
                        case "mi":
                            mid = convert(quantity, temp, "ft");
                            temp = "ft";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }// switch
                    break;
                case "ft"://direct conversions: mi, yd, in, cm
                    switch (unit2) {
                        case "yd":
                        case "mi":
                        case "in":
                        case "cm":
                            mid = quantity;
                            break;
                        case "mm":
                        case "m":
                        case "km":
                            mid = convert(quantity, temp, "cm");
                            temp = "cm";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "in"://direct conversions: mi, m, yd, ft, cm, mm
                    switch (unit2) {
                        case "mi":
                        case "yd":
                        case "ft":
                        case "cm":
                        case "mm":
                        case "m":
                            mid = quantity;
                            break;
                        case "km":
                            mid = convert(quantity, temp, "m");
                            temp = "m";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "km"://direct conversions: m, cm, mm
                    switch (unit2) {
                        case "m":
                        case "mm":
                        case "cm":
                            mid = quantity;
                            break;
                        case "mi":
                        case "yd":
                        case "in":
                        case "ft":
                            mid = convert(quantity, unit1, "cm");
                            temp = "cm";
                            if (unit2.equals("mi")) {
                                mid = convert(mid, temp, "ft");
                                temp = "ft";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "m"://direct conversions: km, in, cm, mm
                    switch (unit2) {
                        case "km":
                        case "mm":
                        case "cm":
                        case "in":
                            mid = quantity;
                            break;
                        case "ft":
                        case "mi":
                        case "yd":
                            mid = convert(quantity, temp, "in");
                            temp = "in";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "mi"://direct conversions: yd, ft, in
                    switch (unit2) {
                        case "yd":
                        case "ft":
                        case "in":
                            mid = quantity;
                            break;
                        case "km":
                        case "m":
                        case "cm":
                        case "mm":
                            mid = convert(quantity, temp, "in");
                            temp = "in";
                            if (unit2.equals("km")) {
                                mid = convert(mid, temp, "cm");
                                temp = "cm";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "mm"://direct conversions: km, m, in, cm
                    switch (unit2) {
                        case "km":
                        case "m":
                        case "in":
                        case "cm":
                            mid = quantity;
                            break;
                        case "mi":
                        case "ft":
                        case "yd":
                            mid = convert(quantity, temp, "in");
                            temp = "in";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "yd"://direct conversions: mi, ft, in, cm
                    switch (unit2) {
                        case "mi":
                        case "ft":
                        case "in":
                        case "cm":
                            mid = quantity;
                            break;
                        case "km":
                        case "m":
                        case "mm":
                            mid = convert(quantity, temp, "cm");
                            temp = "cm";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                default:
                    System.out.println("Something went wrong.");
                    mid = -1;
                    break;
            }// switch
            if (mid != -1)
                mid = convert(mid, temp, unit2);
        } // else
        converted = mid;

        displayConversion(quantity, unit1, converted, unit2);
    }// distanceConverter

    public static void volumeConverter(int[] selectedUnits/*, double quantity*/) {
        String unit1 = volumeUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = volumeUnits.get(selectedUnits[1]).getAbbr(),
                temp = unit1;
        System.out.println("Enter the quantity: ");
        double quantity = input.nextFloat(), converted, mid;
        if (selectedUnits[0] == selectedUnits[1])
            mid = quantity;
        else {
            switch (unit1) {
                case "ft3"://direct conversions: qt, in3
                    switch (unit2) {
                        case "qt":
                        case "in3":
                            mid = quantity;
                            break;
                        case "gal":
                        case "pt":
                        case "tbsp":
                        case "tsp":
                        case "fl oz":
                        case "L":
                        case "m3":
                        case "c":
                        case "mL":
                            mid = convert(quantity, temp, "qt");
                            temp = "qt";
                            if (unit2.equals("L") || unit2.equals("m3") || unit2.equals("c") || unit2.equals("mL")) {
                                mid = convert(mid, temp, "fl oz");
                                temp = "fl oz";
                                if (!unit2.equals("L")) {
                                    mid = convert(mid, temp, "L");
                                    temp = "L";
                                    if (unit2.equals("c")) {
                                        mid = convert(mid, temp, "mL");
                                        temp = "mL";
                                    }//if
                                }//if
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;

                case "in3"://direct conversions: ft3, gal, qt, pt, mL
                    switch (unit2) {
                        case "ft3":
                        case "gal":
                        case "qt":
                        case "pt":
                        case "mL":
                            mid = quantity;
                            break;
                        case "c":
                        case "L":
                        case "tsp":
                        case "m3":
                            mid = convert(quantity, temp, "mL");
                            temp = "mL";
                            break;
                        case "fl oz":
                        case "tbsp":
                            mid = convert(quantity, temp, "qt");
                            temp = "qt";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;

                case "m3"://direct conversions: L, mL
                    switch (unit2) {
                        case "L":
                        case "mL":
                            mid = quantity;
                            break;
                        case "fl oz":
                        case "tbsp":
                        case "gal":
                        case "qt":
                        case "pt":
                            mid = convert(quantity, temp, "L");
                            temp = "L";
                            if (!unit2.equals("fl oz") && !unit2.equals("tbsp")) {
                                mid = convert(mid, temp, "fl oz");
                                temp = "fl oz";
                            }//if
                            break;
                        case "c":
                        case "in3":
                        case "tsp":
                        case "ft3":
                            mid = convert(quantity, temp, "mL");
                            temp = "mL";
                            if (unit2.equals("ft3")) {
                                mid = convert(mid, temp, "in3");
                                temp = "in3";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;

                case "c"://direct conversions: mL
                    switch (unit2) {
                        case "mL":
                            mid = quantity;
                            break;
                        case "m3":
                        case "ft3":
                        case "gal":
                        case "L":
                        case "qt":
                        case "pt":
                        case "fl oz":
                        case "in3":
                        case "tbsp":
                        case "tsp":
                            mid = convert(quantity, temp, "mL");
                            temp = "mL";
                            if (!unit2.equals("m3") && !unit2.equals("L")
                                    && !unit2.equals("in3") && !unit2.equals("tsp")) {
                                mid = convert(mid, temp, "in3");
                                temp = "in3";
                                if (unit2.equals("fl oz") || unit2.equals("tbsp")) {
                                    mid = convert(mid, temp, "qt");
                                    temp = "qt";
                                }//if
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;

                case "fl oz": //direct conversions: gal, L, qt, pt, tbsp, tsp
                    switch (unit2) {
                        case "gal":
                        case "pt":
                        case "tbsp":
                        case "tsp":
                        case "L":
                        case "qt":
                            mid = quantity;
                            break;
                        case "ft3":
                        case "in3":
                            mid = convert(quantity, temp, "qt");
                            temp = "qt";
                            break;
                        case "mL":
                        case "m3":
                        case "c":
                            mid = convert(quantity, temp, "L");
                            unit1 = "L";
                            if (unit2.equals("c")) {
                                mid = convert(mid, temp, "mL");
                                unit1 = "mL";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;

                case "gal": //direct conversions: qt, pt, fl oz, tbsp, in3
                    switch (unit2) {
                        case "pt":
                        case "tbsp":
                        case "in3":
                        case "fl oz":
                        case "qt":
                            mid = quantity;
                            break;
                        case "ft3":
                        case "tsp":
                            mid = convert(quantity, temp, "qt");
                            temp = "qt";
                            break;
                        case "m3":
                        case "L":
                        case "mL":
                        case "c":
                            mid = convert(quantity, temp, "in3");
                            temp = "in3";
                            if (!unit2.equals("mL")) {
                                mid = convert(mid, temp, "mL");
                                temp = "mL";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;

                case "L": //direct conversions: m3, fl oz, mL, tbsp
                    switch (unit2) {
                        case "m3":
                        case "tbsp":
                        case "mL":
                        case "fl oz":
                            mid = quantity;
                            break;
                        case "c":
                        case "in3":
                        case "tsp":
                        case "ft3":
                            mid = convert(quantity, temp, "mL");
                            temp = "mL";
                            if (unit2.equals("ft3")) {
                                mid = convert(mid, temp, "in3");
                                temp = "in3";
                            }//if
                            break;
                        case "qt":
                        case "gal":
                        case "pt":
                            mid = convert(quantity, temp, "fl oz");
                            temp = "fl oz";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;

                case "mL"://direct conversions: m3, L, c, in3, tsp
                    switch (unit2) {
                        case "m3":
                        case "L":
                        case "c":
                        case "in3":
                        case "tsp":
                            mid = quantity;
                            break;
                        case "tbsp":
                        case "pt":
                        case "qt":
                        case "fl oz":
                            mid = convert(quantity, temp, "tsp");
                            temp = "tsp";
                            break;
                        case "gal":
                        case "ft3":
                            mid = convert(quantity, temp, "in3");
                            temp = "in3";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "pt"://direct conversions: gal, qt, fl oz, tbsp, tsp, in3
                    switch (unit2) {
                        case "gal":
                        case "tbsp":
                        case "tsp":
                        case "in3":
                        case "qt":
                        case "fl oz":
                            mid = quantity;
                            break;
                        case "ft3":
                            mid = convert(quantity, temp, "qt");
                            temp = "qt";
                            break;
                        case "L":
                        case "mL":
                        case "c":
                        case "m3":
                            mid = convert(quantity, temp, "fl oz");
                            temp = "fl oz";
                            if (unit2.equals("c") || unit2.equals("m3")) {
                                mid = convert(mid, temp, "mL");
                                temp = "mL";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "qt"://direct conversions: ft3, gal, pt, fl oz, tbsp, tsp, in3
                    switch (unit2) {
                        case "ft3":
                        case "gal":
                        case "pt":
                        case "tbsp":
                        case "tsp":
                        case "in3":
                        case "fl oz":
                            mid = quantity;
                            break;
                        case "L":
                        case "mL":
                        case "m3":
                        case "c":
                            mid = convert(quantity, temp, "in3");
                            temp = "in3";
                            if (!unit2.equals("mL")) {
                                mid = convert(mid, temp, "mL");
                                temp = "mL";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "tbsp"://direct conversions: gal, L, qt, pt, fl oz, tsp
                    switch (unit2) {
                        case "gal":
                        case "pt":
                        case "fl oz":
                        case "tsp":
                        case "L":
                        case "qt":
                            mid = quantity;
                            break;
                        case "mL":
                        case "m3":
                        case "c":
                            mid = convert(quantity, temp, "L");
                            temp = "L";
                            if (unit2.equals("c")) {
                                mid = convert(mid, temp, "mL");
                                temp = "mL";
                            }//if
                            break;
                        case "in3":
                        case "ft3":
                            mid = convert(quantity, temp, "qt");
                            temp = "qt";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "tsp"://direct conversions: qt, pt, fl oz, tbsp, mL
                    switch (unit2) {
                        case "pt":
                        case "fl oz":
                        case "tbsp":
                        case "mL":
                        case "qt":
                            mid = quantity;
                            break;
                        case "L":
                        case "c":
                        case "m3":
                        case "in3":
                            mid = convert(quantity, temp, "mL");
                            temp = "mL";
                            break;
                        case "ft3":
                        case "gal":
                            mid = convert(quantity, temp, "qt");
                            temp = "qt";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                default:
                    System.out.println("Something went wrong.");
                    mid = -1;
                    break;
            }// switch
            if (mid != -1)
                mid = convert(mid, temp, unit2);
        } // else
        converted = mid;
        displayConversion(quantity, unit1, converted, unit2);
    }// volumeConverter

    public static void temperatureConverter(int[] selectedUnits/*, double quantity*/) {
        String unit1 = temperatureUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = temperatureUnits.get(selectedUnits[1]).getAbbr();
        System.out.println("Enter the quantity: ");
        double quantity = input.nextFloat(), converted, mid;

        if (unit1.equals(unit2))
            mid = quantity;
        else {
            switch (unit1) {
                case "F":
                    mid = (quantity - 32) * 5 / 9;
                    if (unit2.equals("K"))
                        mid += 273.15;
                    break;
                case "C":
                    if (unit2.equals("F"))
                        mid = (quantity * 9 / 5) + 32;
                    else
                        mid = quantity + 273.15;
                    break;
                case "K":
                    mid = quantity - 273.15;
                    if (unit2.equals("F"))
                        mid = (mid * 9 / 5) + 32;
                    break;
                default:
                    System.out.println("Something went wrong.");
                    mid = -1;
                    break;
            }// switch
        } // else
        converted = mid;
        displayConversion(quantity, unit1, converted, unit2);
    }// temperatureConverter

    public static void weightConverter(int[] selectedUnits/*, double quantity*/) {
        String unit1 = weightUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = weightUnits.get(selectedUnits[1]).getAbbr(),
                temp = unit1;
        System.out.println("Enter the quantity: ");
        double quantity = input.nextFloat(), converted, mid;
        if (selectedUnits[0] == selectedUnits[1])
            mid = quantity;
        else {
            switch (unit1) {
                case "g"://direct conversions: kg, mg
                    switch (unit2) {
                        case "kg":
                        case "mg":
                            mid = quantity;
                            break;
                        case "lb":
                        case "oz":
                        case "T":
                        case "st":
                            mid = convert(quantity, temp, "kg");
                            temp = "kg";
                            if (!unit2.equals("oz")) {
                                mid = convert(mid, temp, "oz");
                                temp = "oz";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "kg"://direct conversions: g, mg, oz
                    switch (unit2) {
                        case "g":
                        case "mg":
                        case "oz":
                            mid = quantity;
                            break;
                        case "lb":
                        case "T":
                        case "st":
                            mid = convert(quantity, temp, "oz");
                            temp = "oz";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "mg"://direct conversions: kg, g
                    switch (unit2) {
                        case "kg":
                        case "g":
                            mid = quantity;
                            break;
                        case "oz":
                        case "lb":
                        case "st":
                        case "T":
                            mid = convert(quantity, temp, "kg");
                            if (!unit2.equals("oz")) {
                                mid = convert(mid, temp, "oz");
                                temp = "oz";
                            }//id
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "oz"://direct conversions: T, st, kg, lb
                    switch (unit2) {
                        case "lb":
                        case "st":
                        case "T":
                        case "kg":
                            mid = quantity;
                            break;
                        case "mg":
                        case "g":
                            mid = convert(quantity, temp, "kg");
                            temp = "kg";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "lb"://direct conversions: T, st, oz
                    switch (unit2) {
                        case "st":
                        case "T":
                        case "oz":
                            mid = quantity;
                            break;
                        case "kg":
                        case "g":
                        case "mg":
                            mid = convert(quantity, temp, "oz");
                            temp = "oz";
                            if (!unit2.equals("kg")) {
                                mid = convert(mid, temp, "kg");
                                temp = "kg";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "st"://direct conversions: lb, oz
                    switch (unit2) {
                        case "lb":
                        case "oz":
                            mid = quantity;
                            break;
                        case "T":
                        case "kg":
                        case "g":
                        case "mg":
                            mid = convert(quantity, temp, "oz");
                            temp = "oz";
                            if (unit2.equals("g") || unit2.equals("mg")) {
                                mid = convert(mid, temp, "kg");
                                temp = "kg";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "T"://direct conversions: lb, oz
                    switch (unit2) {
                        case "lb":
                        case "oz":
                            mid = quantity;
                            break;
                        case "st":
                        case "kg":
                        case "g":
                        case "mg":
                            mid = convert(quantity, temp, "oz");
                            temp = "oz";
                            if (unit2.equals("g") || unit2.equals("mg")) {
                                mid = convert(mid, temp, "kg");
                                temp = "kg";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                default:
                    System.out.println("Something went wrong.");
                    mid = -1;
                    break;
            }// switch
            if (mid != -1)
                mid = convert(mid, temp, unit2);
        } // else
        converted = mid;
        displayConversion(quantity, unit1, converted, unit2);
    }// weightConverter

    public static void timeConverter(int[] selectedUnits/*, double quantity*/) {
        String unit1 = timeUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = timeUnits.get(selectedUnits[1]).getAbbr(),
                temp = unit1;
        System.out.println("Enter the quantity:");
        double quantity = input.nextFloat(),converted, mid;
        if (selectedUnits[0] == selectedUnits[1])
            mid = quantity;
        else {
            switch (unit1) {
                case "d"://direct conversions: yr, wk, hr, min, sec
                    switch (unit2) {
                        case "sec":
                        case "min":
                        case "hr":
                        case "wk":
                        case "yr":
                            mid = quantity;
                            break;
                        case "mth":
                            mid = convert(quantity, temp, "yr");
                            temp = "yr";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }// switch
                    break;
                case "hr"://direct conversions: yr, wk, d, min, sec
                    switch (unit2) {
                        case "sec":
                        case "min":
                        case "d":
                        case "wk":
                        case "yr":
                            mid = quantity;
                            break;
                        case "mth":
                            mid = convert(quantity, temp, "yr");
                            temp = "yr";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }// switch
                    break;
                case "min"://direct conversions: yr, wk, d, hr, sec
                    switch (unit2) {
                        case "yr":
                        case "wk":
                        case "d":
                        case "hr":
                        case "sec":
                            mid = quantity;
                            break;
                        case "mth":
                            mid = convert(quantity, temp, "yr");
                            temp = "yr";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "mth"://direct conversions: yr
                    switch (unit2) {
                        case "yr":
                            mid = quantity;
                            break;
                        case "wk":
                        case "d":
                        case "min":
                        case "sec":
                        case "hr":
                            mid = convert(quantity, temp, "yr");
                            temp = "yr";
                            if (unit2.equals("sec") || unit2.equals("wk")) {
                                mid = convert(mid, temp, "d");
                                temp = "d";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "sec"://direct conversions: wk, d, hr, min
                    switch (unit2) {
                        case "wk":
                        case "d":
                        case "hr":
                        case "min":
                            mid = quantity;
                            break;
                        case "yr":
                        case "mth":
                            mid = convert(quantity, temp, "d");
                            temp = "d";
                            if (unit2.equals("mth")) {
                                mid = convert(quantity, temp, "yr");
                                temp = "yr";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                case "wk"://direct conversions: d, hr, min, sec
                    switch (unit2) {
                        case "d":
                        case "hr":
                        case "min":
                        case "sec":
                            mid = quantity;
                            break;
                        case "yr":
                        case "mth":
                            mid = convert(quantity, temp, "d");
                            temp = "d";
                            if (unit2.equals("mth")) {
                                mid = convert(quantity, temp, "yr");
                                temp = "yr";
                            }//if
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }// switch
                    break;
                case "yr"://direct conversions: mth, d, hr, min
                    switch (unit2) {
                        case "mth":
                        case "d":
                        case "hr":
                        case "min":
                            mid = quantity;
                            break;
                        case "sec":
                        case "wk":
                            mid = convert(quantity, temp, "d");
                            temp = "d";
                            break;
                        default:
                            System.out.println("Something went wrong.");
                            mid = -1;
                            break;
                    }//switch
                    break;
                default:
                    System.out.println("Something went wrong.");
                    mid = -1;
                    break;
            }// switch
            if (mid != -1)
                mid = convert(mid, temp, unit2);
        } // else
        converted = mid;
        displayConversion(quantity, unit1, converted, unit2);
    }// timeConverter

    public static void displayConversion(double quantity1, String unit1, double quantity2, String unit2) {
        System.out.printf("%f%s  =  %f%s", quantity1, unit1, quantity2, unit2);
    }// displayConversion

    public static double convert(double quantity, String unit1, String unit2) {
        double converted = 0;
        for (var f : Conversion.values()) {
            if (unit1.equals(f.getUnit1()) && unit2.equals(f.getUnit2()))
                converted = quantity * f.getFactor();
            else if (unit1.equals(f.getUnit2()) && unit2.equals(f.getUnit1()))
                converted = quantity / f.getFactor();
        }//for
        return converted;
    }//convert

    /*public static void converterTest(int menuSelection) {
        switch (menuSelection) {
            case 1: // Distance
                for (int i = 1; i <= distanceUnits.size(); i++, System.out.print("\n")) {
                    System.out.println(distanceUnits.get(i).getName());
                    for (int j = 1; j <= distanceUnits.size(); j++) {
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
                for (int i = 1; i <= volumeUnits.size(); i++, System.out.print("\n")) {
                    System.out.println(volumeUnits.get(i).getName());
                    for (int j = 1; j <= volumeUnits.size(); j++) {
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
                for (int i = 1; i <= temperatureUnits.size(); i++, System.out.print("\n")) {
                    System.out.println(temperatureUnits.get(i).getName());
                    for (int j = 1; j <= temperatureUnits.size(); j++) {
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
                for (int i = 1; i <= weightUnits.size(); i++, System.out.print("\n")) {
                    System.out.println(weightUnits.get(i).getName());
                    for (int j = 1; j <= weightUnits.size(); j++) {
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
                for (int i = 1; i <= timeUnits.size(); i++, System.out.print("\n")) {
                    System.out.println(timeUnits.get(i).getName());
                    for (int j = 1; j <= timeUnits.size(); j++) {
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
    }//converterTest*/
}// Converter
