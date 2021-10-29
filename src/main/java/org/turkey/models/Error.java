package org.turkey.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("code")
    @Expose
    private List<String> code = null;
    @SerializedName("price")
    @Expose
    private List<String> price = null;
    @SerializedName("min_amount")
    @Expose
    private List<String> minAmount = null;
    @SerializedName("subAmount")
    @Expose
    private List<String> subAmount = null;
    @SerializedName("quantity")
    @Expose
    private List<String> quantity = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Error() {
    }

    /**
     *
     * @param minAmount
     * @param code
     * @param price
     * @param subAmount
     * @param quantity
     */
    public Error(List<String> code, List<String> price, List<String> minAmount, List<String> subAmount, List<String> quantity) {
        super();
        this.code = code;
        this.price = price;
        this.minAmount = minAmount;
        this.subAmount = subAmount;
        this.quantity = quantity;
    }

    public List<String> getCode() {
        return code;
    }

    public void setCode(List<String> code) {
        this.code = code;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    public List<String> getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(List<String> minAmount) {
        this.minAmount = minAmount;
    }


    public List<String> getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(List<String> subAmount) {
        this.subAmount = subAmount;
    }

    public List<String> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<String> quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", price=" + price +
                ", minAmount=" + minAmount +
                ", subAmount=" + subAmount +
                ", quantity=" + quantity +
                '}';
    }
}
