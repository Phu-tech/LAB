/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obj;

import java.io.Serializable;
import model.Validation;

/**
 *
 * @author USER
 */
public class Flight implements Serializable {

    private String flightNumber, departureCity,
            destinationCity, departureTime, arrivalTime;
    private int availableSeats;
    private boolean seats[];

    public Flight() {
    }

    public Flight(String flightNumber, String departureCity, String destinationCity,
            String departureTime, String arrivalTime, int availableSeats, boolean seats[]) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.seats = seats;

    }

    public void createFlightNumber() {
        this.flightNumber = Validation.getFight("Fight Number(Fxxxx): ");
        this.departureCity = Validation.getString("Departure City: ");
        this.destinationCity = Validation.getString("Destination City: ");
        this.departureTime = Validation.getTime("Departure Time(hh:mm): ");
        this.arrivalTime = Validation.getTime("Arrival Time(hh:mm): ", this.departureTime);
        this.availableSeats = Validation.getInt("Available Seats(1-200): ", 1, 200);
        this.seats = new boolean[this.availableSeats];

    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int isAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isAvalableSeats(int seatsN) {
        if (seatsN < 1 || seatsN > (seats.length)) {
            return false; // if index out seat - false;
        }
        if (seats[seatsN - 1]) {  // -true(no available) -false(available seats)
            return false;
        } else {
            return true;
        }
    }

    public void bookSeats(int seatNumber) {
        if (seatNumber >= 1 && seatNumber <= seats.length) {
            if (!seats[seatNumber - 1] ) {
                seats[seatNumber - 1] = true;// if true -> seat[i-1] = true;  
                availableSeats--;// discre seats - 1
            }
       
        }
    }

    @Override
    public String toString() {
        return String.format("|  %-8s|  %-11s|  %-13s"
                + "|  %-16s|  %-13s|  %-15d|",
                flightNumber.toUpperCase(), departureCity.toUpperCase(), destinationCity.toUpperCase(), departureTime, arrivalTime, availableSeats);
    }
    public String displays(){
        return String.format("|  %-8s|  %-11s|  %-13s"
                + "|  %-16s|  %-13s|  %-15d|",
                flightNumber.toUpperCase(), departureCity.toUpperCase(), destinationCity.toUpperCase(), departureTime, arrivalTime, isAvailableSeats());
    }
}
