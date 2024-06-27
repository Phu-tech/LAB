/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Obj.Crew;
import Obj.Flight;
import Obj.Reservation;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author USER
 */
public class FlightManage {

    ArrayList<Flight> listFlight = new ArrayList<>();
    ArrayList<Reservation> listReservation = new ArrayList<>();

    public final String FILE_PATH = "Flight.dat";
    public final String File_PATH_2 = "Reservation.dat";

    public void addAFlight() {

        Flight flight = new Flight();
        flight.createFlightNumber();
        if (!checkExitID(flight.getFlightNumber())) {
            listFlight.add(flight);
            saveToFile();
        } else {
            System.err.println("EXIST BY ID - CAN'T ADD");
        }

    }

    public boolean checkExitID(String id) {
        for (Flight flight : listFlight) {
            if (flight.getFlightNumber().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public void saveToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            oos.writeObject(listFlight);
            // System.out.println("Flight save successfull!");
        } catch (IOException e) {
        }
    }

    public void readToFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            listFlight = (ArrayList<Flight>) ois.readObject();
            sortDate();
            for (Flight flight : listFlight) {
                System.out.println(flight);
            }
        } catch (IOException | ClassNotFoundException e) {
        }

    }

    public void printAll(int choice) {
        switch (choice) {
            case 1:
                System.out.println("|  NUMBER  |  DEPARTURE  |  DESTINATION  "
                        + "|  DEPARTURE TIME  |  ARRIVAL TIME | AVAILABLE SEATS |");
                readToFile();
                break;
            case 2:
                readReservationToFile();
                break;
        }
        Validation.backToMenu();

    }

    public void saveReservationToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(File_PATH_2));
            oos.writeObject(listReservation);
        } catch (IOException e) {
        }
    }

    public void readReservationToFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(File_PATH_2));
            listReservation = (ArrayList<Reservation>) ois.readObject();
            for (Reservation reservation : listReservation) {
                System.out.println(reservation);
            }
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    public void reservationFlight() {
        String departureIn = Validation.getString("Departure search: ");
        String arrivalIn = Validation.getString("Destination search: ");
        String timeDeparture = Validation.getTime("Departure time search: ");
        String timeArrival = Validation.getTime("Arrival time search: ", timeDeparture);

        ArrayList<Flight> availableFlight = new ArrayList<>();

        for (Flight flight : listFlight) {
            if (departureIn.toLowerCase().equalsIgnoreCase(flight.getDepartureCity().toLowerCase())
                    && arrivalIn.toLowerCase().equalsIgnoreCase(flight.getDestinationCity().toLowerCase())
                    && timeDeparture.contains(flight.getDepartureTime())
                    && timeArrival.contains(flight.getArrivalTime())) {
                availableFlight.add(flight);
            }
        }
        if (availableFlight.isEmpty()) {
            System.err.println("FLYGHT not FOUND!");
        } else {
            System.out.println("  |  NUMBER  |  DEPARTURE  |  DESTINATION  |  DEPARTURE TIME  |  ARRIVAL TIME | AVAILABLE SEATS |");
            for (int i = 0; i < availableFlight.size(); i++) {
                System.out.println((i + 1) + " " + availableFlight.get(i));
            }
            int flightChoice = Validation.getInt("Sellect a flight by number: ", 1, availableFlight.size());

            Flight selectFlight = availableFlight.get(flightChoice - 1);
            String passengerName = Validation.getString("Enter your name: ");
            String contractDetail = Validation.getString("Enter your contract detail: ");

            Reservation reservation = new Reservation(passengerName, contractDetail, selectFlight);
            listReservation.add(reservation);
            saveReservationToFile();
            System.out.println("Resevartion successful! " + " - " + "ID RES: " + reservation.getResercationId());
        }

        Validation.backToMenu();
    }

    public void checkInFlight() {
        try {
            String reservationIDCheck = Validation.getString("Enter your reservation ID: ");
            Reservation reservationCheckIn = null;

            for (Reservation reservation : listReservation) {
                if (reservation.getResercationId().equalsIgnoreCase(reservationIDCheck)) {
                    reservationCheckIn = reservation;
                    break;
                }
            }

            if (reservationCheckIn == null) {
                System.out.println("Reservation ID not Found!");
            } else {
                Flight flight = reservationCheckIn.getFlight();
                System.out.println("------------FLIGHT DETAIL------------");
                System.out.println("|  NUMBER  |  DEPARTURE  |  DESTINATION  |  DEPARTURE TIME  |  ARRIVAL TIME | AVAILABLE SEATS |");
                System.out.println(flight);

                if (flight.isAvailableSeats() == 0) {
                    System.out.println("No available seats in this flight!");
                    return;
                } else {
                    System.out.println("Available seats: " + flight.isAvailableSeats());
                    int seatNumber = Validation.getInt("Enter seat number ( 1 - " + flight.isAvailableSeats() + " ): ", 1, flight.isAvailableSeats());
                    if (flight.isAvalableSeats(seatNumber)) { //
                        flight.bookSeats(seatNumber); //make seat[i] book 
                        System.out.println("---------Information------------ ");
                        System.out.println("Reservation ID: " + reservationCheckIn.getResercationId());
                        System.out.println("Passenger Name: " + reservationCheckIn.getPassengerName());
                        System.out.println("       |  NUMBER  |  DEPARTURE  |  DESTINATION  |  DEPARTURE TIME  |  ARRIVAL TIME | AVAILABLE SEATS |");
                        System.out.println("Flight " + flight.displays());
                        System.out.println("Seat Number: " + seatNumber);
                        System.out.println("--------CHECK-IN SUCCESSFUL!----");
                    } else {
                        System.out.println("Seats number " + seatNumber + " is already book!");
                    }
                }
            }
        } catch (NullPointerException e) {

        }
        Validation.backToMenu();
    }

    public void sortDate() {
        Collections.sort(listFlight, (o1, o2) -> {
            return -o1.getDepartureTime().compareTo(o2.getDepartureTime());
        });
    }

    public void CrewManagement() {
        try {
            Crew crew;
            if (Validation.logInAdmin()) {
                String flightCheck = Validation.getString("Enter flight wanna manage: ");
                for (Flight flight : listFlight) {
                    if (flightCheck.equalsIgnoreCase(flight.getFlightNumber())) {
                        System.out.println("|  NUMBER  |  DEPARTURE  |  DESTINATION  |  DEPARTURE TIME  |  ARRIVAL TIME | AVAILABLE SEATS |");
                        System.out.println(flight);
                        System.out.println("-------------Enter Information crew-------------");
                        String name = Validation.getString("Enter name: ");
                        String role1 = Validation.getRole();
                        crew = new Crew(name, role1, flight);
                        Flight flight1 = crew.getFlight();
                        System.out.println("-----------Employee Infor------------");
                        System.out.println("NAME : " + crew.getName());
                        System.out.println("ROLE : " + crew.getRole());
                        System.out.println("          |  NUMBER  |  DEPARTURE  |  DESTINATION  |  DEPARTURE TIME  |  ARRIVAL TIME | AVAILABLE SEATS |");
                        System.out.println("In flight " + flight1);
                        System.out.println("-------------SUCCESS!-------------");
                    } else {
                        System.out.println("NOT FOUND!");
                    }
                }
            } else {
                System.err.println("You're can't access this feather!");
            }
        } catch (NullPointerException e) {

        }

        Validation.backToMenu();
    }
}
