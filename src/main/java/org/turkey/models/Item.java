package org.turkey.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.turkey.services.HTTPRequest.DBConnector;
import org.turkey.services.NumberWithComma;

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
    @SerializedName("no_po_line")
    @Expose
    private BigInteger noPoLine;

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
     * @param noPoLine
     */
    public Item(String code, Float price, BigInteger amount, BigInteger minAmount, BigInteger noPoLine) {
        super();
        this.code = code;
        this.price = price;
        this.amount = amount;
        this.minAmount = minAmount;
        this.noPoLine = noPoLine;
    }

    public Item(String code, Float price, BigInteger minAmount) {
        this.code = code;
        this.price = price;
        this.amount = new BigInteger(0+"");
        this.minAmount = minAmount;
    }

public BigInteger getNoPoLine() {
        return noPoLine;
    }

    public void setNoPoLine(BigInteger noPoLine) {
        this.noPoLine = noPoLine;
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

    public String getPriceWithComma(){
        return NumberWithComma.addComma(price);
    }

    public String getAmountWithComma(){
        return NumberWithComma.addComma(amount);
    }

    public String getMinAmountWithComma(){
        return NumberWithComma.addComma(minAmount);
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", minAmount=" + minAmount +
                ", noPoLine=" + noPoLine +
                '}';
    }

    public StatusInApp getOrderStatus() {
        StatusInApp res = new DBConnector().getOrderStatus(getCode());
        if (res.equals(StatusInApp.ไม่มีการสั่งสินค้า) && amount.compareTo(minAmount)<1){
            return StatusInApp.จำนวนคงเหลือน้อยกว่าที่กำหนด;
        }
        return res;
    }
}