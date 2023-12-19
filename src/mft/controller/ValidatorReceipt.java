package mft.controller;

import java.util.regex.Pattern;

public class ValidatorReceipt {
    public static boolean amountValidator(int amount){
String amount1= String.valueOf(amount);
        return Pattern.matches("^[1-9]+$",amount1);
    }
}


