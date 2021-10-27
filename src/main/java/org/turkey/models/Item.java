package org.turkey.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class Item {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("price")
    @Expose
    private Float price;
    @SerializedName("amount")
    @Expose
    private BigInteger amount;
    @SerializedName("min_amount")
    @Expose
    private BigInteger minAmount;

    /**
     * No args constructor for use in serialization
     *
     */
    public Item() {
    }

    /**
     *
     * @param minAmount
     * @param amount
     * @param code
     * @param price
     */
    public Item(String code, Float price, BigInteger amount, BigInteger minAmount) {
        super();
        this.code = code;
        this.price = price;
        this.amount = amount;
        this.minAmount = minAmount;
    }

    public Item(String code, Float price, BigInteger minAmount) {
        this.code = code;
        this.price = price;
        this.amount = new BigInteger(0+"");
        this.minAmount = minAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public BigInteger getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigInteger minAmount) {
        this.minAmount = minAmount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", minAmount=" + minAmount +
                '}';
    }
}