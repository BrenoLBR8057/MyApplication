package com.example.supermarket.ui.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Supermarket implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "product")
    private String product;
    @ColumnInfo(name = "quantify")
    private int quantify;
    @ColumnInfo(name = "price")
    private double price;
    @ColumnInfo(name = "total")
    private double total;
    @ColumnInfo(name = "finalTotal")
    private double finalTotal;

    public Supermarket(String product, int quantify, double price, double total, double finalTotal){
        this.price = price;
        this.product = product;
        this.quantify = quantify;
        this.total = total;
        this.finalTotal = finalTotal;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        this.finalTotal += finalTotal;
    }

    public String getTotalAsString() {
        return String.valueOf(total);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct(){
        return product;
    }

    public int getQuantify(){
        return quantify;
    }

    public double getPrice(){
        return price;
    }

    public String getQuantifyAsString(){
        return String.valueOf(quantify);
    }

    public String getPriceAsString(){
        return String.valueOf(price);
    }

    public void setProduct(String product){
        this.product = product;
    }

    public void setQuantify(int quantify){
        this.quantify = quantify;
    }

    public void setPrice(double price){
        this.price = price;
    }
}
