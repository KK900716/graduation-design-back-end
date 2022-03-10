package com.example.utils;

import java.text.DecimalFormat;

public class Round {
    public static float round(float x){
        DecimalFormat decimalFormat=new DecimalFormat("#.00");
        return Float.parseFloat(decimalFormat.format(x));
    }
}
