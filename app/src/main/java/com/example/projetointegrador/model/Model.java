package com.example.projetointegrador.model;

import java.io.Serializable;

public class Model implements Serializable {
    private int id = 0;
    private String product;
    private double price;
    private int quantify;

    public Model(String product, double price, int quantify){
        this.product = product;
        this.price = price;
        this.quantify = quantify;
    }

    public void setId(int id){this.id = id;}

    public int getId(){return id;}

    public String getDescription(){return product;}

    public String getPriceLikeString(){return String.valueOf(price);}

    public String getQuantifyLikeString(){return String.valueOf(quantify);}
}
