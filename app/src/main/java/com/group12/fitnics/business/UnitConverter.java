package com.group12.fitnics.business;

import com.group12.fitnics.exceptions.InvalidUnits;

public class UnitConverter {

    public static double KGToLB(double kg) {
        return kg * 2.205; // 1 kg = 2.205 lb
    }

    public static double LBToKg(double lb) {
        return lb / 2.205; // 1 kg = 2.205 lb
    }

    public static double FTToCM(double ft) {
        return ft * 30.48; // 1 ft = 30.48 cm
    }

    public static double CMToFT(double cm) {
        return cm / 30.48; // 1 ft = 30.48 cm
    }

    public static String convertUnitToString(double unit, int decimalPlace) throws InvalidUnits {
        if((unit > 0.0)){
            //Any unit and convert to specified decimal place
            String format = "%."+decimalPlace+"f";

            return String.format(format, unit);
        }else {
            throw new InvalidUnits("Empty units not allowed");
        }

    }

}
