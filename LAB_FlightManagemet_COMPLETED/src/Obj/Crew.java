/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obj;

import model.Validation;

/**
 *
 * @author USER
 */
public class Crew {

    //pilots, flight attendants, and ground staff
    private String name;
    private String role;
    private Flight flight;

    public Crew(String name, String role, Flight flight) {
        this.name = name;
        this.role = role;
        this.flight = flight;
    }

    public Crew() {
    }

    public void createCrew() {
        this.name = Validation.getString("Enter name : ");
        this.role = Validation.getString("Enter role(pilots - flight attendants - ground staff): "); ////pilots, flight attendants, and ground staff       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Name : " + name + "\nRole : " + role + "\nFlight : " + flight ;
    }

    

}
