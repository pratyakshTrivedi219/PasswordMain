package com.example.passwordchecker;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class PasswordStrengthChecker {
    public static String main(String password) {
        int entropy = strengthEstimator(password);
        String str = TimeToCrack.timeToCrack(entropy);

        String result = null;
        if(entropy==0){
            result="PASSWORD TOO SHORT";
            return result;
        } else if (entropy==1) {
            result="COMMON PASSWORD, USE A MORE UNIQUE AND A SECURE PASSWORD";
            return result;
        }else {
            if (entropy > 15 && entropy <= 25) {
                result = "WEAK PASSWORD\n";
            } else if (entropy > 25 && entropy <= 35) {
                result = "MODERATE PASSWORD\n";
            } else if (entropy > 35 && entropy <= 45) {
                result = "STRONG PASSWORD\n";
            } else {
                result = "VERY STRONG PASSWORD\n";
            }
        }
        result += str;

        return result;
    }
    public static int strengthEstimator(String password){
        char[] passArr = password.toCharArray();
        int passLength = passArr.length;
        int[] charCount = new int[5];

        //[lowercase(26), uppercase(26), digit(10), whitespace(1), special character(32)]

        int charRange = 0;

        System.out.println("Evaluating password length");
//        PrintLoadingAnimation.printLoadingAnimation2000();
        if(passLength<8){
            return 0;
        } else {
            System.out.println("Adequate password length.....");
//            PrintLoadingAnimation.printLoadingAnimation1000();
            //check for common passwords.
            System.out.println("Checking for the passwords in the common passwords list...");
//            PrintLoadingAnimation.printLoadingAnimation1000();

            //check common password.
            if(!checkCommonPasswordFile(password)){
                return 1;
            }
            System.out.println("Not found any match in common passwords....");
            System.out.println("Evaluating password entropy.....");

//            PrintLoadingAnimation.printLoadingAnimation1000();

            //check individual character presence.
            for(char element: passArr){
                if(Character.isUpperCase(element)){
                    //Uppercase letter.
                    charCount[0]++;
                } else if (Character.isLowerCase(element)){
                    //Lowercase letter.
                    charCount[1]++;
                } else if(Character.isDigit(element)){
                    //Digit
                    charCount[2]++;
                } else if (Character.isWhitespace(element)) {
                    //Whitespace character.
                    charCount[3]++;
                } else {
                    //Special character.
                    charCount[4]++;
                }
            }
            //calculate password character range
            if(charCount[0]>0){
                //if lowercase present
                charRange+=26;
            }
            if(charCount[1]>0){
                //if uppercase present
                charRange+=26;
            }
            if(charCount[2]>0){
                //if digit present
                charRange+=10;
            }
            if(charCount[3]>0){
                //if whitespace present
                charRange+=1;
            }
            if(charCount[4]>0) {
                //if special character present
                charRange+=32;
            }
            return charRange;
        }
    }
    public static double entropyCalculator(int charRange, String Password){
        double passwordEntropy = (Math.log10(Math.pow(charRange, Password.length()))/Math.log(2));
        System.out.println("Your password entropy is: "+passwordEntropy);
        return passwordEntropy;
    }
    public static boolean checkCommonPasswordFile(String password){
        try{
            //used the rockYou text file to check for the presence of common passwords.(commonly used for brute force attacks in john the ripper password attacks)
            File Obj = new File("src/main/resources/static/rockyou.txt");
            Scanner Reader = new Scanner(Obj);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                if(data.equals(password)){
                    return false;
                }
            }
            Reader.close();
            return true;
        }catch(FileNotFoundException e){
            System.out.println("File not found exception");
        }
        return true;
    }
}
