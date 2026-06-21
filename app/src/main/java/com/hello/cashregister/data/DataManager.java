package com.hello.cashregister.data;

import com.hello.cashregister.models.Product;
import com.hello.cashregister.models.PurchaseHistory;

import java.util.ArrayList;

public class DataManager
{
    private static ArrayList<Product> products;
    private static ArrayList<PurchaseHistory> purchaseHistory;
    public static DataManager shared = new DataManager();

    private DataManager()
    {
        this.products = new ArrayList<Product>();
        this.purchaseHistory = new ArrayList<PurchaseHistory>();
    }

    // setters
    public void setProducts(ArrayList<Product> products) {DataManager.products = products;}
    public void setPurchaseHistory(ArrayList<PurchaseHistory> history) { DataManager.purchaseHistory = history;}

    //Getters
    public ArrayList<Product> getProducts() {return products;}
    public ArrayList<PurchaseHistory> getPurchaseHistory() {return purchaseHistory;}
    public PurchaseHistory getPurchase(int i){ return purchaseHistory.get(i);}

    public void addProduct(Product product)
    {
        DataManager.products.add(product);
    }
    public void addPurchase(PurchaseHistory purchase){DataManager.purchaseHistory.add(purchase);}
}
