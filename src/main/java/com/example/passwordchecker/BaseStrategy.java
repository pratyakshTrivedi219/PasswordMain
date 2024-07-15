package com.example.passwordchecker;

public interface BaseStrategy {
    int strengthEstimator(String password);
    double entropyCalculator(int charRange, String password);
    boolean checkCommonPasswordFile(String password);
    String timeToCrack(double entropy);
}