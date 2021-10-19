package org.turkey.models;

import java.math.BigInteger;
import java.util.ArrayList;

public abstract class Order {
    private String code;
    private Information partner;// Don't know eiei
    private float totalPrice;
    private ArrayList<OrderLine> orders;

    public Order(String code, Information partner, float totalPrice){
        this.code = code;
        this.partner = partner;
        this.totalPrice = totalPrice;
        orders = new ArrayList<>();
    }

    public void addOrderLine(OrderLine orderLine){
        orders.add(orderLine);
    }

    public String getCode() {return code;}
    public float getTotalPrice() {return totalPrice;}
    public String getPartner() {return partner.getName();}
    public void addToTotal(float price){this.totalPrice += price;}
}
