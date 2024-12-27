package org.example.utill;

public class InputValidator {
    public static boolean isValidateAmount(String input){
        try {
            Double.parseDouble(input);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean isPositiveAmount(double amount){
        return amount>0; //true
    }
}
