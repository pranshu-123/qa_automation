package com.qa.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Ankur Jaiswal
 */
public class TextUtils {

    /**
     * Round the number to defined decimal places
     * @param value - Value to be rounded
     * @param places - Decimal places to be returned
     * @return - Rounded number
     */
    public static double roundNumber(double value, int places) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
