package com.library.util;

import java.util.Scanner;

public class ConsoleUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int inputInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val >= 0) return val;
                System.out.println("  ⚠  Please enter a non-negative number.");
            } catch (NumberFormatException e) {
                System.out.println("  ⚠  Invalid number. Try again.");
            }
        }
    }

    public static void printDivider() {
        System.out.println("─".repeat(92));
    }

    public static void printHeader(String title) {
        System.out.println();
        printDivider();
        System.out.printf("  %s%n", title.toUpperCase());
        printDivider();
    }

    public static void pause() {
        System.out.print("\n  Press ENTER to continue...");
        scanner.nextLine();
    }
}
