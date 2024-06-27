/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author USER
 */
public class Validation {

    private static Scanner sc = new Scanner(System.in);

    public static int getInt(String msg, int min, int max) {
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }
        while (true) {
            try {
                System.out.print(msg);
                int n = Integer.parseInt(sc.nextLine());
                if (min <= n && n <= max) {
                    return n;
                }
                System.err.println("Input in rangce " + min + " - " + max);
            } catch (NumberFormatException e) {
                System.err.println("WRONG FORMAT!");
            }
        }
    }

    public static String getFight(String msg) {
        while (true) {
            try {
                Pattern pattern = Pattern.compile("F\\d{4}");
                String input = getString(msg);
                Matcher match = pattern.matcher(input.toUpperCase());
                if (match.find()) {
                    return input;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.err.println("follow as: Fxxxx");
            }

        }

    }

    public static String getString(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String str = sc.nextLine();
                if (str.isEmpty()) {
                    System.err.println("EMPTY STRING!");
                }
                return str;
            } catch (Exception e) {
                System.err.println("WRONG FORMAT!");
            }
        }
    }

    public static boolean getYN(String msg) {
        while (true) {
            System.out.print(msg);
            String yn = sc.nextLine();
            if (yn.equalsIgnoreCase("y")) {
                return true;
            } else if (yn.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.err.println("ONLY Y/N");
            }
        }
    }

    public static String getTime(String msg) {
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        while (true) {
            try {
                System.out.print(msg);
                String inputTime = sc.nextLine();
                LocalTime time = LocalTime.parse(inputTime, formatTime);
                return String.format("%s", time);

            } catch (NumberFormatException | DateTimeParseException e) {
                System.err.println("FORMAT: HH:mm!");
            }
        }
    }

    public static String getTime(String msg1, String time1) { // departure time < arrival time
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        while (true) {
            try {
                System.out.print(msg1);
                String inputTime = sc.nextLine();
                LocalTime time = LocalTime.parse(inputTime, formatTime);
                if (time.isAfter(LocalTime.parse(time1, formatTime))) {
                    return String.format("%s", time);
                } else {
                    System.err.println("Time desparture < Time arrivail");
                }

            } catch (NumberFormatException | DateTimeParseException e) {
                System.err.println("FORMAT: HH:mm! OR time desparture < time arrivail");
            }
        }
    }
// enterkey
    public static void backToMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Enter to return to continue....");
        sc.nextLine();
    }
//check / get date
    public static String getDate(String msg) {
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String str = "";
        boolean check = true;
        do {
            System.out.print(msg);
            String inputDate = sc.nextLine();
            if (inputDate.isEmpty()) {
                System.err.println("Empty Date!");
            } else {
                try {
                    LocalDate.parse(inputDate, formatTime);
                    str = inputDate;
                    check = false;
                    if (Integer.parseInt(str.substring(0, 4)) <= 1920
                            && Integer.parseInt(str.substring(0, 4)) >= 2024) {
                        System.err.println("In range 1920 - 2024");
                        check = true;
                    }
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.err.println("FORMAT: yyyy-mm-dd!");
                }
            }
        } while (check);
        return str.trim();
    }

    public static String getRole() {////pilots, flight attendants, and ground staff
        while (true) {
            try {
                String str = getString("Enter role(pilots, flight attendants, and ground staff): ");
                if (str.equalsIgnoreCase("pilots") || str.equalsIgnoreCase("flight attendants") || str.equalsIgnoreCase("ground staff")) {
                    return str;
                }
            } catch (Exception e) {
            }

        }

    }
//check admin access
    public static boolean logInAdmin() {
        String userName = Validation.getString("Enter your user name: ");
        String password = Validation.getString("Enter your password : ");
        if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            System.out.println("LOGIN SUCCESSFUL !");
            return true;
        }
        System.out.println("LOGIN FAIL!");
        return false;
    }
}
