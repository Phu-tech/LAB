/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class Vehicle implements Serializable {

    private int id;
    private String name;
    private String color;
    private double price;
    private String brand;
    private String type;
    private int year;
    public Vehicle() {
    }

    public Vehicle(int id, String name, String color, double price, String brand, String type,int year) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.type = type;
        this.year = year;
    }

    public void createVehicle() {
        this.id = Validation.getInt("ID: ", 1, Integer.MAX_VALUE);
        this.name = Validation.getString("Name: ");
        this.color = Validation.getString("Color: ");
        this.price = Validation.getDouble("Price: ");
        this.brand = Validation.getString("Brand: ");
        this.type = Validation.getString("Type: ");
        this.year = Validation.getInt("Year: ", 1800, 2024);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("|ID: %-2s| NAME: %-4s| COLOR: %-2s| PRICE: %-4.2f| BRAND: %-4s| TYPE: %-4s| YEAR: %-2d|",
                id, name.toUpperCase(), color, price, brand, type,year);
    }

}
