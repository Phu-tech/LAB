/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Scanner;
import model.FlightManage;
import model.Validation;

/**
 *
 * @author USER
 */
public class Menu {
    Scanner sc = new Scanner(System.in);
    public Menu() {
        FlightManage fli = new FlightManage();
        boolean yn;
        int choice;
        do {
            System.out.println("");
            System.err.println(" Flight Management System/He thong quan ly bay");
            System.out.println("");
            System.out.println(" --------------------------------------------");
            System.out.println("|1.Add Flight schedule(Them)                 |");
            System.out.println("|2.Passenger Reservation and Booking(Dang ki)|");
            System.out.println("|3.Passenger Check-In(Kiem tra)              |");
            System.out.println("|4.Crew Management(QLI Phi hanh doan         |");
            System.out.println("|5.Save to File.(Luu)                        |");
            System.out.println("|6.Print All.(In)                            |");
            System.out.println("|7.Exit. (Thoat)                             |");
            System.out.println(" --------------------------------------------");

            choice = Validation.getInt("Choice: ", 1, 7);
            switch (choice) {
                case 1:
                    do {
                        System.out.println("-----------ADD NEW FLIGHT SCHEDULE----------------");
                        fli.addAFlight();
                        yn = Validation.getYN("GO BACK TO MENU(Y/N): ");
                    } while (!yn);
                    break;
                case 2:
                    do {
                        System.out.println("---------------Passenger Reservation and Booking------------");
                        fli.reservationFlight();
                        yn = Validation.getYN("GO BACK TO MENU(Y/N): ");
                    } while (!yn);                   
                    break;
                case 3:
                    do {
                        System.out.println("-----Passenger Check-In and Seat Allocation and Availability----------");
                        fli.checkInFlight();
                        yn = Validation.getYN("GO BACK TO MENU(Y/N): ");
                    } while (!yn);
                    break;
                case 4:
                    do {     
                        System.out.println("--------------Crew Management (ADMIN ACCESS)---------");
                        fli.CrewManagement();
                        yn = Validation.getYN("GO BACK TO MENU(Y/N): ");
                    } while (!yn);
                    
                    break;
                case 5:
                    System.out.println("......................Saving........................");
                    fli.saveToFile();
                    fli.saveReservationToFile();
                    break;
                case 6:
                    int in = Validation.getInt("1.Print flight.\n2.Print Reservation.\nEnter: ", 1, 2);
                    System.out.println("-----------------------Print ALL---------------------------");
                    fli.printAll(in);
                    break;
                case 7:
                    System.out.println(".........................BYEEE........................");
                    break;
            }
        } while (choice != 7);
    }
}
