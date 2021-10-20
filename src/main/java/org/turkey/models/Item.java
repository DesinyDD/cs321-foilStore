package org.turkey.models;

import java.math.BigInteger;

public class Item {
    private String colorCode;
    private BigInteger amount;
    private float price;
    private BigInteger minAmount;
    private Enum<Status> status;

    public Item(String colorCode, BigInteger amount, float price, BigInteger minAmount){
        this.colorCode = colorCode;
        this.amount = amount;
        this.price = price;
        this.minAmount = minAmount;
        this.status = Status.ไม่มีการสั่งสินค้า;
    }

    public void addStock(BigInteger quantity){
        this.amount = this.amount.add(quantity);
    }

    public void saleItem(BigInteger quantity){
        this.amount = this.amount.subtract(quantity);
    }

    public void havePO(){
        this.status = Status.มีการสั่งสินค้า;
    }

    public void noPO(){
        this.status = Status.ไม่มีการสั่งสินค้า;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public Enum<Status> getStatus() {
        return status;
    }

    public String getColorCode() {
        return colorCode;
    }

    public BigInteger getMinAmount() {
        return minAmount;
    }

    public float getPrice() {
        return price;
    }
}
