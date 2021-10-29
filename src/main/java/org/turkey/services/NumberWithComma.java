package org.turkey.services;

import java.math.BigInteger;
import java.text.NumberFormat;

public class NumberWithComma {
    public static String addComma(BigInteger number){
        NumberFormat format = NumberFormat.getInstance();
        return format.format(number);
    }

    public static String addComma(float number){
        NumberFormat format = NumberFormat.getInstance();
        return format.format(number);
    }
}
