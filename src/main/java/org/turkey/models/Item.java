package org.turkey.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("price")
    @Expose
    private Float price;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("min_amount")
    @Expose
    private Integer minAmount;

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
    public Item(String code, Float price, Integer amount, Integer minAmount) {
        super();
        this.code = code;
        this.price = price;
        this.amount = amount;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

}