package com.example.passwordchecker;

public class TimeToCrack {
    public static String timeToCrack(double entropy){
        //formula to evaluate time required to crack a password.
        long time =(long)Math.pow(2, entropy)/500;
        //take a range of time values......
        if(time>60 && time <3600){
            //represent in minutes.
            time = time/60;
            return ("It will approximately take "+time+" minutes for someone to crack a password.");
        } else if (time>3600 && time<86400) {
            //represent in hours.
            time = time/3600;
            return ("It will approximately take "+time+" hours for someone to crack a password.");
        } else if (time>86400 && time<2592000){
            //represent in days.
            time/=86400;
            return ("It will approximately take "+time+" days for someone to crack a password.");
        } else if (time>2592000 && time<31104000){
            //represent in months.
            time/=2592000;
            return ("It will approximately take "+time+" months for someone to crack a password.");
        } else if(time>31104000) {
            //represent in years.
            time/= 31556952;
            return ("It will approximately take "+time+ " years for someone to crack a password.");
        }
        else{
            return ("It will be cracked in seconds");
        }
    }
}