package com.hello.cashregister.models;

import android.net.wifi.aware.PublishConfig;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class PurchaseHistory implements Serializable
{
    private String name;
    private int quantity;
    private double total;
    private LocalDateTime purchaseDate;

    public PurchaseHistory()
    {
        this.name = "";
        this.quantity = 0;
        this.total = 0.0;
        this.purchaseDate = LocalDateTime.now();
    }

    public PurchaseHistory(String name, int qty, double total, LocalDateTime dt)
    {
        this.name = name;
        this.quantity = qty;
        this.total = total;
        this.purchaseDate = dt;
    }

    //Setters
    public void setName(String name){this.name= name;}
    public void setQuantity(int qty){this.quantity = qty;}
    public void setTotal(double tt){this.total = tt;}
    public void setPurchaseDate(LocalDateTime dt){this.purchaseDate = dt;}

    //Getters
    public String getName(){return this.name;}
    public int getQuantity(){return this.quantity;}
    public double getTotal(){return this.total;}
    public LocalDateTime getPurchaseDate(){return this.purchaseDate;}
}
