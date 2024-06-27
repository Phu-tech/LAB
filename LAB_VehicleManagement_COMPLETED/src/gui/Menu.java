/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import model.Validation;
import model.VehicleManage;

/**
 *
 * @author USER
 */
public class Menu {

    public Menu() throws IOException, ClassNotFoundException {
        VehicleManage ve = new VehicleManage();
        boolean yn;
        int choice;
        do {
            System.out.println("");
            System.err.println("        MENU VEHICLE MANAGEMENT            ");
            System.out.println("|-----------------------------------------|");
            System.out.println("|1.Add new Vehicle.                       |");
            System.out.println("|2.Checking exits Vehicle                 |");
            System.out.println("|3.Updating vehicle                       |");
            System.out.println("|4.Delete vehicle                         |");
            System.out.println("|5.Search vehicle                         |");
            System.out.println("|6.Display vehicle list                   |");
            System.out.println("|7.Save data to file                      |");
            System.out.println("|8.Print vehicle list                     |");
            System.out.println("|9.Exit                                   |");
            System.out.println("|-----------------------------------------|");
            choice = Validation.getInt("Input number: ", 1, 9);
            switch (choice) {
                case 1:
                    do {
                        ve.addVehicle();
                        yn = Validation.getYN("Go back to Menu(Y/N):");
                    } while (!yn);
                    System.out.println("--------Success!--------");
                    break;
                case 2:
                    do {
                        int id = Validation.getInt("Input ID wanna check: ", 1, Integer.MAX_VALUE);
                        ve.checkExistVehicle(id);
                        yn = Validation.getYN("Go back to Menu(Y/N):");
                    } while (!yn);

                    System.out.println("--------Success!---------");
                    break;
                case 3:
                    do {
                        int id = Validation.getInt("Input ID wanna update: ", 1, Integer.MAX_VALUE);
                        ve.updateVehicle(id);
                        yn = Validation.getYN("Go back to Menu(Y/N):");
                    } while (!yn);
                    break;
                case 4:
                    int id = Validation.getInt("Input ID wanna delete: ", 1, Integer.MAX_VALUE);
                    ve.deleteVehicle(id);
                    break;
                case 5:
                    int ch = Validation.getInt("1.Search by NAME \n2.Search by ID \n Input:", 1, 2);
                    ve.searchVehicle(ch);

                    break;
                case 6:
                    int ch1 = Validation.getInt("1.Show ALL \n"
                            + "2.Show all (descending by price_vehicle) \n Input:", 1, 2);
                   
                    ve.displayVehiclie(ch1);
                    System.out.println("-----------Success!-------");
                    Validation.backMenu();
                    break;
                case 7:
                    ve.writeFile();
                    System.out.println("-------Success!--------");
                    break;
                case 8:
                    int ch2 = Validation.getInt("1.Show ALL \n"
                            + "2.Show all (descending by price_vehicle) \n Input:", 1, 2);
                    ve.printAll(ch2);
                    break;
                case 9:
                    System.out.println("BYEEEE ........");
                    break;
            }
        } while (choice != 9);
    }
}
