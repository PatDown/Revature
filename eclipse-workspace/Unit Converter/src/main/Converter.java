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
    
    public static void distanceConverter(int[] selectedUnits, double quantity) {
        String unit1 = distanceUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = distanceUnits.get(selectedUnits[1]).getAbbr(),
                temp = unit1;
        //System.out.println("Enter the quantity: ");
        double /*quantity = input.nextFloat(),*/ converted, mid;
        if (selectedUnits[0] == selectedUnits[1]) {
            mid = quantity;
        } else {
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
                    switch (unit2){
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
                    switch (unit2){
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
                            if (unit2.equals("mi")){
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
                            if (unit2.equals("km")){
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
                    switch (unit2){
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
            mid = convert(mid, temp, unit2);
        } // else
        converted = mid;
        
        displayConversion(quantity, unit1, converted, unit2);
    }// distanceConverter

    public static void volumeConverter(int[] selectedUnits, double quantity) {
        String unit1 = volumeUnits.get(selectedUnits[0]).getAbbr(), unit2 = volumeUnits.get(selectedUnits[1]).getAbbr();
        //System.out.println("Enter the quantity: ");
        double /*quantity = input.nextFloat(),*/ converted = 0;
        String tempUnit;
        if (selectedUnits[0] == selectedUnits[1]) {
            converted = quantity;
        } else {
            switch (unit1) {
                case "ft3"://direct conversions: qt, in3
                    if (unit2.equals("in3"))
                        converted = quantity * Conversion.FT3_IN3.getFactor();
                    else{
                        converted = quantity * Conversion.FT3_QT.getFactor();
                        if (!unit2.equals("qt")){
                            switch(unit2){
                                case "gal":
                                    converted /= Conversion.GAL_QT.getFactor();
                                    break;
                                case "pt":
                                    converted = quantity * Conversion.QT_PT.getFactor();
                                    break;
                                case "tbsp":
                                    converted = quantity * Conversion.QT_TBSP.getFactor();
                                    break;
                                case "tsp":
                                    converted = quantity * Conversion.QT_TSP.getFactor();
                                    break;
                                default:
                                    converted = quantity * Conversion.QT_FLOZ.getFactor();
                                    if (!unit2.equals("fl oz")){
                                        converted /= Conversion.L_FLOZ.getFactor();
                                        if (!unit2.equals("L")){
                                            if (unit2.equals("m3"))
                                                converted = convert(converted, unit1, unit2);
                                            else{
                                                converted *= Conversion.L_ML.getFactor();
                                                if (unit2.equals("c"))
                                                    converted /= Conversion.C_ML.getFactor();
                                            }//else
                                        }//if
                                    }//if
                                    break;
                            }//switch
                        }//if
                    }//else
                    break;
                    
                case "in3"://direct conversions: ft3, gal, qt, pt, mL
                    switch(unit2){
                        case "ft3":
                            converted = quantity / Conversion.FT3_IN3.getFactor();
                            break;
                        case "gal":
                            converted = quantity / Conversion.GAL_IN3.getFactor();
                            break;
                        case "pt":
                            converted = quantity / Conversion.PT_IN3.getFactor();
                            break;
                        default:
                            if (unit2.equals("mL") || unit2.equals("c")){
                                converted = quantity * Conversion.IN3_ML.getFactor();
                                if (unit2.equals("c"))
                                    converted /= Conversion.C_ML.getFactor();
                            }//if
                            else {
                                converted = quantity / Conversion.QT_IN3.getFactor();
                                if (!unit2.equals("qt")){
                                    switch(unit2){
                                        case "tbsp":
                                            converted *= Conversion.QT_TBSP.getFactor();
                                            break;
                                        case "tsp":
                                            converted *= Conversion.QT_TSP.getFactor();
                                            break;
                                        default:
                                            converted = quantity * Conversion.QT_FLOZ.getFactor();
                                            if (!unit2.equals("fl oz")){
                                                converted /= Conversion.L_FLOZ.getFactor();
                                                if (!unit2.equals("L"))
                                                    if (unit2.equals("m3"))
                                                        converted /= Conversion.M3_L.getFactor();
                                            }//if
                                            break;
                                    }//switch
                                }//if
                            }//else
                            break;
                    }//switch
                    break;
                    
                case "m3"://direct conversions: L, mL
                    if (unit2.equals("mL") || unit2.equals("c")){
                        converted = convert(quantity, unit1, "mL");
                        unit1 = "mL";
                        if (unit2.equals("c"))
                            converted = convert(converted, unit1, unit2);
                    } else {
                        converted = convert(quantity, unit1, "L");
                        unit1 = "L";
                        if (unit2.equals("m3") || unit2.equals("tbsp"))
                            converted = convert(converted, unit1, unit2);
                        else{
                            converted = convert(converted, unit1, "fl oz");
                            unit1 = "fl oz";
                            if (!unit2.equals("fl oz")){
                                if (unit2.equals("gal") || unit2.equals("pt") || unit2.equals("tsp"))
                                    converted = convert(converted, unit1, unit2);
                                else {
                                    converted = convert(converted, unit1, "qt");
                                    unit1 = "qt";
                                    if (!unit2.equals("qt"))
                                        converted = convert(converted, unit1, unit2);
                                }//else
                            }//if    
                        }//else
                    }//else
                    break;
                    
                case "c"://direct conversions: mL
                    converted = convert(quantity, unit1, "mL");
                    unit1 = "mL";
                    if (!unit2.equals("mL")) {
                        if (unit2.equals("m3") || unit2.equals("in3") || unit2.equals("tsp"))
                            converted = convert(quantity, unit1, unit2);
                        else {
                            converted = convert(quantity, unit1, "L");
                            unit1 = "L";
                            if (unit2.equals("tbsp")) {
                                converted = convert(converted, unit1, unit2);
                            } else {
                                converted = convert(converted, unit1, "fl oz");
                                unit1 = "fl oz";
                                if (!unit2.equals("fl oz")) {
                                    if (unit2.equals("gal") || unit2.equals("pt")) {
                                        converted = convert(converted, unit1, unit2);
                                    } else {
                                        converted = convert(converted, unit1, "qt");
                                        unit1 = "qt";
                                        if (!unit2.equals("qt")) {
                                            converted = convert(converted, unit1, unit2);
                                        }
                                    }//else
                                }//if    
                            }//else
                        }//else
                    }//if
                    break;
                    
                case "fl oz": //direct conversions: gal, L, qt, pt, tbsp, tsp
                    switch (unit2) {
                        case "gal":
                        case "pt":
                        case "tbsp":
                        case "tsp":
                            converted = convert(quantity, unit1, unit2);
                            break;
                        case "qt":
                        case "ft3":
                        case "in3":
                            converted = convert(quantity, unit1, "qt");
                            unit1 = "qt";
                            if (!unit2.equals("qt"))
                                converted = convert(converted, unit1, unit2);
                            break;
                        default:
                            converted = convert(quantity, unit1, "L");
                            unit1 = "L";
                            if (!unit2.equals("L")){
                                if (unit2.equals("m3"))
                                    converted = convert(converted, unit1, unit2);
                                else {
                                    converted = convert(converted, unit1, "mL");
                                    unit1 = "mL";
                                    if (unit2.equals("c"))
                                        converted = convert(converted, unit1, unit2);
                                }//else
                            }//if
                            break;
                    }//switch
                    break;
                    
                case "gal": //direct conversions: qt, pt, fl oz, tbsp, in3
                    switch (unit2) {
                        case "pt":
                        case "tbsp":
                        case "in3":
                            converted = convert(quantity, unit1, unit2);
                            break;
                        case "qt":
                        case "ft3":
                            converted = convert(quantity, unit1, "qt");
                            unit1 = "qt";
                            if (!unit2.equals("qt"))
                                converted = convert(converted, unit1, unit2);
                            break;
                        default:
                            converted = convert(quantity, unit1, "fl oz");
                            unit1 = "fl oz";
                            if (unit2.equals("tsp"))
                                converted = convert(converted, unit1, unit2);
                            else {
                                converted = convert(converted, unit1, "L");
                                unit1 = "L";
                                if (!unit2.equals("L")) {
                                    if (unit2.equals("m3"))
                                        converted = convert(converted, unit1, unit2);
                                    else {
                                        converted = convert(converted, unit1, "mL");
                                        unit1 = "mL";
                                        if (unit2.equals("c"))
                                            converted = convert(converted, unit1, unit2);
                                    }//else
                                }//if
                            }//else
                            break; //else
                    }//switch
                    break;
                    
                case "L": //direct conversions: m3, fl oz, mL, tbsp
                    switch (unit2) {
                        case "m3":
                        case "tbsp":
                            converted = convert(quantity, unit1, unit2);
                            break;
                        case "mL":
                        case "c":
                            converted = convert(quantity, unit1, "mL");
                            unit1 = "mL";
                            if (unit2.equals("c"))
                                converted = convert(converted, unit1, unit2);
                            break;
                        default:
                            converted = convert(quantity, unit1, "fl oz");
                            unit1 = "fl oz";
                            if (!unit2.equals("fl oz")){
                                if (unit2.equals("gal") || unit2.equals("pt") || unit2.equals("tsp"))
                                    converted = convert(converted, unit1, unit2);
                                else {
                                    converted = convert(converted, unit1, "qt");
                                    unit1 = "qt";
                                    if (!unit2.equals("qt"))
                                        converted = convert(converted, unit1, unit2);
                                }//else
                            }//if
                            break; //else
                    }//switch
                    break;

                case "mL"://direct conversions: m3, L, c, in3, tsp
                    if (unit2.equals("m3") || unit2.equals("c") || unit2.equals("in3") || unit2.equals("tsp"))
                        converted = convert(quantity, unit1, unit2);
                    else {
                        converted = convert(quantity, unit1, "L");
                        unit1 = "L";
                        if (unit2.equals("tbsp"))
                            converted = convert(converted, unit1, unit2);
                        else {
                            converted = convert(converted, unit1, "fl oz");
                            unit1 = "fl oz";
                            if (!unit2.equals("fl oz")){
                                if (unit2.equals("gal") || unit2.equals("pt"))
                                    converted = convert(converted, unit1, unit2);
                                else {
                                    converted = convert(converted, unit1, "qt");
                                    unit1 = "qt";
                                    if (!unit2.equals("qt"))
                                        converted = convert(converted, unit1, unit2);
                                }//else
                            }//if    
                        }//else
                    }//else
                    break;
                case "pt"://direct conversions: gal, qt, fl oz, tbsp, tsp, in3
                    switch(unit2) {
                        case "gal":
                        case "tbsp":
                        case "tsp":
                        case "in3":
                            converted = convert(quantity, unit1, unit2);
                            break;
                        case "qt":
                        case "ft3":
                            converted = convert(quantity, unit1, "qt");
                            unit1 = "qt";
                            if (unit2.equals("ft3"))
                                converted = convert(converted, unit1, unit2);
                            break;
                        case "fl oz":
                        case "L":
                        case "mL":
                        case "c":
                        case "m3":
                            converted = convert(quantity, unit1, "fl oz");
                            unit1 = "fl oz";
                            if (!unit2.equals("fl oz")){
                                converted = convert(quantity, unit1, "L");
                                unit1 = "L";
                                if (!unit2.equals("L")){
                                    if (unit2.equals("m3"))
                                        converted = convert(converted, unit1, unit2);
                                    else {
                                        converted = convert(converted, unit1, "mL");
                                        unit1 = "mL";
                                        if (unit2.equals("c"))
                                            converted = convert(converted, unit1, unit2);
                                    }//else
                                }//if
                            }//if
                            break;
                        default:
                            System.out.println("Invalid units");
                            break;
                    }//switch
                    break;
                case "qt"://direct conversions: ft3, gal, pt, fl oz, tbsp, tsp, in3
                    switch(unit2){
                        case "ft3":
                        case "gal":
                        case "pt":
                        case "tbsp":
                        case "tsp":
                        case "in3":
                            converted = convert(quantity, unit1, unit2);
                            break;
                        default:
                            converted = convert(quantity, unit1, "fl oz");
                            unit1 = "fl oz";
                            if (!unit2.equals("fl oz")){
                                converted = convert(converted, unit1, "L");
                                unit1 = "L";
                                if (!unit2.equals("L")){
                                    if (unit2.equals("m3"))
                                        converted /= Conversion.M3_L.getFactor();
                                    else{
                                        converted *= Conversion.L_ML.getFactor();
                                        if (unit2.equals("c"))
                                            converted /= Conversion.C_ML.getFactor();
                                    }//else
                                }//if
                            }//if
                            break;
                    }//switch
                    break;
                case "tbsp"://direct conversions: gal, L, qt, pt, fl oz, tsp
                    switch (unit2){
                        case "gal":
                        case "pt":
                        case "fl oz":
                        case "tsp":
                            converted = convert(quantity, unit1, unit2);
                            break;
                        case "L":
                        case "mL":
                        case "m3":
                        case "c":
                            converted = convert(quantity, unit1, "L");
                            unit1 = "L";
                            if (!unit2.equals("L")){
                                if (unit2.equals("mL")){
                                    converted = convert(converted, unit1, "mL");
                                    unit1 = "mL";
                                    if (unit2.equals("c"))
                                        converted = convert(converted, unit1, unit2);
                                } else
                                    converted = convert(converted, unit1, unit2);
                            }//if
                            break;
                        case "qt":
                        case "in3":
                        case "ft3":
                            converted = convert(quantity, unit1, "qt");
                            unit1 = "qt";
                            if (!unit2.equals("qt"))
                                converted = convert(converted, unit1, unit2);
                        default:
                            
                            break;
                    }//switch
                    break;
                case "tsp"://direct conversions: qt, pt, fl oz, tbsp, mL
                    switch (unit2){
                        case "pt":
                        case "fl oz":
                        case "tbsp":
                            converted = convert(quantity, unit1, unit2);
                            break;
                        case "mL":
                        case "L":
                        case "c":
                        case "m3":
                        case "in3":
                            converted = convert(quantity, unit1, "mL");
                            unit1 = "mL";
                            if (!unit2.equals("mL"))
                                converted = convert(converted, unit1, unit2);
                            break;
                        case "qt":
                        case "ft3":
                        case "gal":
                            converted = convert(quantity, unit1, "qt");
                            unit1 = "qt";
                            if (!unit2.equals("qt"))
                                converted = convert(converted, unit1, unit2);
                            break;
                        default:
                            break;
                    }//switch
                    break;
                default:
                    break;
            }// switch
        } // else
        displayConversion(quantity, unit1, converted, unit2);
    }// volumeConverter

    public static void temperatureConverter(int[] selectedUnits, double quantity) {
        String unit1 = temperatureUnits.get(selectedUnits[0]).getAbbr(),
                unit2 = temperatureUnits.get(selectedUnits[1]).getAbbr();
        //System.out.println("Enter the quantity: ");
        double /*quantity = input.nextFloat(),*/ converted;

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
                        converted = quantity + 273.15;
                    }
                    break;
                default:// case "K"
                    converted = quantity - 273.15;
                    if (unit2.equals("F")) {
                        converted = (converted * 9 / 5) + 32;
                    }
                    break;
            }// switch
        } // else
        displayConversion(quantity, unit1, converted, unit2);
    }// temperatureConverter

    public static void weightConverter(int[] selectedUnits, double quantity) {
        String unit1 = weightUnits.get(selectedUnits[0]).getAbbr(), unit2 = weightUnits.get(selectedUnits[1]).getAbbr();
        //System.out.println("Enter the quantity: ");
        double /*quantity = input.nextFloat(),*/ converted = 0;
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
                            converted *= 35.274;
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
                            converted = quantity * 35.274;
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
                            converted *= 35.274;
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
                            converted = quantity / 35.274;
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
                                converted /= 35.274;
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
                            converted /= 35.274;
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
                            converted /= 35.274;
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

    public static void timeConverter(int[] selectedUnits, double quantity) {
        String unit1 = timeUnits.get(selectedUnits[0]).getAbbr(), unit2 = timeUnits.get(selectedUnits[1]).getAbbr();
        //System.out.println("Enter the quantity:");
        double /*quantity = input.nextFloat(),*/ converted;
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

    public static void displayConversion(double quantity1, String unit1, double quantity2, String unit2) {
        System.out.printf("%f%s  =  %f%s", quantity1, unit1, quantity2, unit2);
    }// displayConversion
    
    public static double convert(double quantity, String unit1, String unit2){
        double converted = 0;
        for (var f : Conversion.values()){
            if (unit1.equals(f.getUnit1()) && unit2.equals(f.getUnit2()))
                converted = quantity * f.getFactor();
            else if (unit1.equals(f.getUnit2()) && unit2.equals(f.getUnit1()))
                converted = quantity / f.getFactor();

        }//for
        return converted;
    }//convert
    
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
