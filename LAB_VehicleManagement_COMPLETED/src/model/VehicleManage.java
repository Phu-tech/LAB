/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class VehicleManage {

    List<Vehicle> vehicleList = new ArrayList<>();
    boolean yn;
    public final String FILE_PATH = "Vehicle.dat";
    public ArrayList<Vehicle> vehicleSearch = new ArrayList<>();

    public void addVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.createVehicle();
        if (!checkExistId(vehicle.getId())) {
            vehicleList.add(vehicle);
            writeFile();
            System.out.println("Success!");
        }else{
            System.err.println("Fail!-EXIST BY ID");
        }
       
    }

    public void readFile(int choice) {
        switch (choice) {
            case 1:
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
                    vehicleList = (ArrayList<Vehicle>) ois.readObject();
                    sortById();
                    for (Vehicle ve : vehicleList) {
                        System.out.println(ve);
                    }
                } catch (IOException | ClassNotFoundException e) {
                }
                break;
            case 2:
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
                    vehicleList = (ArrayList<Vehicle>) ois.readObject();
                    System.out.println("-------------------------");
                    sortByPrice();
                    for (Vehicle ve : vehicleList) {
                        System.out.println(ve);
                    }
                } catch (IOException | ClassNotFoundException e) {
                }
                break;
        }

    }
    
    public void writeFile() {
        try {
            ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            ois.writeObject(vehicleList);
        } catch (IOException e) {
        }
    }

    public void displayVehiclie(int choice) {
        switch (choice) {
            case 1:// show all               
                readFile(1);
                break;
            case 2:// show all by (descending by price_vehicle)                
                readFile(2);
                break;
        }

    }

    public boolean checkExistId(int id) {
        boolean check = false;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }

    public void checkExistVehicle(int id) {
        boolean check = false;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId() == id) {
                System.out.println("-----Exit Vehicle by ID----");
                check = true;
                break;
            }
        }
        if (!check) {
            System.err.println("----NO FOUND!----");
        }
    }

    public void updateVehicle(int id) {
        Scanner sc = new Scanner(System.in);
        Vehicle vehicleEdit = null;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId() == id) {
                vehicleEdit = vehicle;
                break;

            }
        }
        if (vehicleEdit != null) {
            System.out.println("----------------------------------------");
            System.out.println(vehicleEdit);
            System.out.println("----------------------------------------");
            System.out.print("Id update: ");
            String idEdit = sc.nextLine();

            System.out.print("Name update: ");
            String nameEdit = sc.nextLine();
            System.out.print("Color update: ");
            String colorEdit = sc.nextLine();
            double priceEdit = Validation.getDouble("Price update: ");
            System.out.print("Brand update: ");
            String brandEdit = sc.nextLine();
            System.out.print("Type update: ");
            String typeEdit = sc.nextLine();
            int yearEdit = Validation.getInt("Year update: ", 1800, 2024);

            if (nameEdit != null && !nameEdit.isEmpty()) {
                vehicleEdit.setName(nameEdit);
            }
            if (colorEdit != null) {
                vehicleEdit.setColor(colorEdit);
            }
            if (priceEdit != 0) {
                vehicleEdit.setPrice(priceEdit);
            }
            if (brandEdit != null) {
                vehicleEdit.setBrand(brandEdit);
            }
            if (typeEdit != null) {
                vehicleEdit.setType(typeEdit);
            }
            if (yearEdit != 0) {
                vehicleEdit.setYear(yearEdit);
            }

            yn = Validation.getYN("CONFIRM (Y/N)");
            if (yn) {
                
                writeFile();
                System.out.println("Success!");
            } else {
                System.err.println("FAIL!");
            }
        } else {
            System.err.println("Vehicle does not exist!");
        }
    }

    public void deleteVehicle(int id) {
        Vehicle vehicleDelete = null;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId() == id) {
                vehicleDelete = vehicle;
                break;

            }
        }
        if (vehicleDelete != null) {

            System.out.println(vehicleDelete);
            System.out.println("------------------------------");
            yn = Validation.getYN("CONFIRM DELETE(Y/N)");
            if (yn) {
                vehicleList.remove(vehicleDelete);
                writeFile();
                System.out.println("Success!");
            } else {
                System.err.println("FAIL!");
            }
        } else {
            System.err.println("NOT FOUND!");
        }
    }

    public void sortByName() {
        Collections.sort(vehicleSearch, (o1, o2) -> {
            return -o1.getName().compareTo(o2.getName());
        });
    }

    public void sortByPrice() {
        Collections.sort(vehicleList, (o1, o2) -> {
            return -Double.compare(o1.getPrice(), o2.getPrice());
        });
    }

    public void sortById() {
        Collections.sort(vehicleList, (o1, o2) -> {
            return Integer.compare(o1.getId(), o2.getId());
        });
    }

    public void searchVehicle(int choice) {
        boolean found = false;
        vehicleSearch.clear();
        switch (choice) {
            case 1: // search by name                
                String nameSearch = Validation.getString("Name wanna search: ");
                for (Vehicle vehicle : vehicleList) {
                    if (vehicle.getName().toLowerCase().contains(nameSearch.toLowerCase())) {
                        vehicleSearch.add(vehicle);
                    }
                }
                if (vehicleSearch.isEmpty()) {
                    System.err.println("NOT FOUND!");
                } else {
                    sortByName();
                    for (Vehicle ve : vehicleSearch) {
                        System.out.println(ve);
                    }
                }
                break;
            case 2:
                int idSearch = Validation.getInt("ID wanna search: ", 1, Integer.MAX_VALUE);
                for (Vehicle vehicle : vehicleList) {
                    if (vehicle.getId() == (idSearch)) {
                        vehicleSearch.add(vehicle);
                    }
                }
                if (vehicleSearch.isEmpty()) {
                    System.err.println("NOT FOUND!");
                } else {
                    for (Vehicle ve : vehicleSearch) {
                        System.out.println(ve);
                    }
                }
                break;
        }
        System.out.println("------------------------------------");

    }

    public void printAll(int choice) {
        displayVehiclie(choice);
    }

}
