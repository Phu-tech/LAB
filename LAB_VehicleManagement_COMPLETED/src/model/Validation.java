/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Validation {

    private static Scanner sc = new Scanner(System.in);

    // xu li kieu int
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
                } else {
                    System.err.println("In range " + min + " - " + max);
                }
            } catch (NumberFormatException e) {
                System.err.println("Wrong Format!");
            }
        }
    }

    // xu li kieu double
    public static double getDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                double d = Double.parseDouble(sc.nextLine());
                if (d > 0.1) {
                    return d;
                } else {
                    System.err.println("Input number > 0!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Wrong Format!");
            }

        }
    }
    // xu li kieu String
    public static String getString(String msg){
        while (true) {            
            System.out.print(msg);
            String st = sc.nextLine();
            if(st.isEmpty()){
                System.err.println("Empty String!");
            }else{
                return st;
            }
        }
    }
    
    // xu li kieu Y/N
    public static boolean getYN (String msg){
        while (true) {            
            System.out.print(msg);
            String yn = sc.nextLine();
            if(yn.equalsIgnoreCase("y")){
                return true;
            }else if(yn.equalsIgnoreCase("n")){
                return false;
            }else{
                System.err.println("Only Y/N!");
            }
        }
    }
    public static void backMenu(){
        System.out.println("Enter to continue...");
        sc.nextLine();        
    }    
    
}
