package com.hello.cashregister.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int quantity;
    private double price;

    public Product()
    {
        this.name = "";
        this.quantity = 0;
        this.price = 0.0;
    }

    public Product(String name, int qty, double pr)
    {
        this.name = name;
        this. quantity = qty;
        this.price = pr;
    }

    //Setters
    public void setName(String name) {this.name = name;}
    public void setQuantity(int qty) {this.quantity = qty;}
    public void setPrice(double pr) {this.price = pr;}

    //Getters
    public String getName() { return this.name;}
    public int getQuantity() { return this.quantity;}
    public double getPrice() { return this.price;}

    @Override
    public String toString() {
        return name + " " + quantity  + " "  + price;
    }
}
