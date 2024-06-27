/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obj;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class Reservation implements Serializable {

    public static int count = 0;
    public String resercationId;
    public String passengerName;
    public String contractDetails;
    public Flight flight;

    public Reservation() {
    }

    public Reservation(String passengerName, String contractDetails, Flight flight) {
        this.resercationId = String.valueOf("R" + (++count));
        this.passengerName = passengerName;
        this.contractDetails = contractDetails;
        this.flight = flight;
    }

    public String getResercationId() {
        return resercationId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getContractDetails() {
        return contractDetails;
    }

    public Flight getFlight() {
        if (flight != null) {
            return flight;
        }
        return null;
    }

    @Override
    public String toString() {
        return "EesercationId : " + resercationId + "\nPassengerName: " + passengerName
                + "\nContractDetails :" + contractDetails + "\nFlight :" + flight;
    }

}
