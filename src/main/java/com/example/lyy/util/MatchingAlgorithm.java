package com.example.lyy.util;

public class MatchingAlgorithm {

    public static String getMappingValue(String value) {
        boolean result = value.matches(".*[A-Z].*");
        if(result){
            String code = value.substring(0, 1);
            String number = value.substring(1);
            int tempNumber = new Integer(number.toString());
            tempNumber = tempNumber + 1;
            number = Integer.toString(tempNumber);
            if(number.length()==4){
                int tempCode = Integer.valueOf(code);
                tempCode = tempCode + 1;
                code = String.valueOf(tempCode);
            }
        }else {
            int tempValue = new Integer(value.toString());
            tempValue = tempValue + 1;
            value = new Integer(tempValue).toString();
            if(value.length()==5){
                value = "A000";
            }
        }
        value = ("0000" + value.toString()).substring(value.toString().length());
        return value;
    }
}
