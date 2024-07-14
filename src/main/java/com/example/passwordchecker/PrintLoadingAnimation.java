package com.example.passwordchecker;

public class PrintLoadingAnimation {
    public static void printLoadingAnimation2000() {
        for (int i = 0; i < 20; i++) {
            System.out.print("*");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }
    public static void printLoadingAnimation1000() {
        for (int i = 0; i < 20; i++) {
            System.out.print("*");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }
}
