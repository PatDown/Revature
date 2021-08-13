package main;

/**
 *
 * @author Pat Down
 */
public enum Conversion {
    //The constants in this enum should be named such that the greater value comes first. 
    
    //Length Conversions
    MI_YD (1760, "mi", "yd"),
    MI_FT (5280, "mi", "ft"),
    MI_IN (63360, "mi", "in"),
    KM_M (1000, "km", "m"),
    KM_CM (100000, "km", "cm"),
    KM_MM (1000000, "km", "mm"),
    M_IN (39.37, "m", "in"),
    M_CM (100, "m", "cm"),
    M_MM (1000, "m", "mm"),
    YD_FT (3, "yd", "ft"),
    YD_IN (36, "yd", "in"),
    YD_CM (91.44, "yd", "cm"),
    FT_IN (12, "ft", "in"),
    FT_CM (30.48, "ft", "cm"),
    IN_CM (2.54, "in", "cm"),
    IN_MM (25.4, "in", "mm"),
    CM_MM (10, "cm", "mm"),
    
    //Volume Conversions
    M3_L (1000, "m3", "L"),
    M3_ML (1000000, "m3", "mL"),
    FT3_QT (29.922, "ft3", "qt"),
    FT3_IN3 (1728, "ft3", "in3"),
    GAL_QT (4, "gal", "qt"),
    GAL_PT (8, "gal", "pt"),
    GAL_FLOZ (128, "gal", "fl oz"),
    GAL_TBSP (256, "gal", "tbsp"),
    GAL_IN3 (231, "gal", "in3"),
    L_FLOZ (33.814, "L", "fl oz"),
    L_ML (1000, "L", "mL"),
    L_TBSP (67.628, "L", "tbsp"),
    QT_PT (2, "qt", "pt"),
    QT_FLOZ (32, "qt", "fl oz"),
    QT_TBSP (64, "qt", "tbsp"),
    QT_TSP (192, "qt", "tsp"),
    QT_IN3 (57.75, "qt", "in3"),
    PT_FLOZ (16, "pt", "fl oz"),
    PT_TBSP (32, "pt", "tbsp"),
    PT_TSP (96, "pt", "tsp"),
    PT_IN3 (28.875, "pt", "in3"),
    C_ML (240, "c", "mL"),
    FLOZ_TBSP (2, "fl oz", "tbsp"),
    FLOZ_TSP (6, "fl oz", "tsp"),
    IN3_ML (16.387, "in3", "mL"),
    TBSP_TSP (3, "tbsp", "tsp"),
    TSP_ML (4.929, "tsp", "mL"),
    
    //Weight Conversions
    T_LB (2000, "T", "lb"),
    T_OZ (32000, "T", "oz"),
    ST_LB (14, "st", "lb"),
    ST_OZ (224, "st", "oz"),
    KG_G (1000, "kg", "g"),
    KG_MG (1000000, "kg", "mg"),
    KG_OZ (35.274, "kg", "oz"),
    LB_OZ (16, "lb", "oz"),
    G_MG (1000, "g", "mg"),
    
    //Time Conversions
    YR_MTH (12, "yr", "mth"),
    YR_D (365, "yr", "d"),
    YR_HR (8760, "yr", "hr"),
    YR_MIN (525600, "yr", "min"),
    WK_D (7, "wk", "d"),
    WK_HR (168, "wk", "hr"),
    WK_MIN (10080, "wk", "min"),
    WK_SEC (604800, "wk", "sec"),
    D_HR (24, "d", "hr"),
    D_MIN (1440, "d", "min"),
    D_SEC (86400, "d", "sec"),
    HR_MIN (60, "hr", "min"),
    HR_SEC (3600, "hr", "sec"),
    MIN_SEC (60, "min", "sec");
        
    private final double factor;
    private final String unit1;
    private final String unit2;
    
    private Conversion(double factor, String unit1, String unit2){
        this.factor = factor;
        this.unit1 = unit1;
        this.unit2 = unit2;
    }//constructor
    
    public double getFactor(){
        return factor;
    }//getFactor

    public String getUnit1() {
        return unit1;
    }//getUnit1

    public String getUnit2() {
        return unit2;
    }//getUnit2
    
}//Conversion
