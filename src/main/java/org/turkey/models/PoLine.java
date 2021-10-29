package org.turkey.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.turkey.services.NumberWithComma;

import java.math.BigInteger;

public class PoLine {

    @SerializedName("id")
    @Expose
    private BigInteger id;
    @SerializedName("po_code")
    @Expose
    private String poCode;
    @SerializedName("color_code")
    @Expose
    private String colorCode;
    @SerializedName("quantity")
    @Expose
    private BigInteger quantity;
    @SerializedName("price_per_unit")
    @Expose
    private float pricePerUnit;
    @SerializedName("item")
    @Expose
    private Item item;

    /**
     * No args constructor for use in serialization
     *
     */
    public PoLine() {
    }

    /**
     *
     * @param item
     * @param quantity
     * @param poCode
     * @param colorCode
     * @param id
     * @param pricePerUnit
     */
    public PoLine(BigInteger id, String poCode, String colorCode, BigInteger quantity, float pricePerUnit, Item item) {
        this.id = id;
        this.poCode = poCode;
        this.colorCode = colorCode;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.item = item;
    }
    public PoLine(String poCode, String colorCode, BigInteger quantity, float pricePerUnit) {
        this.id = id;
        this.poCode = poCode;
        this.colorCode = colorCode;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.item = item;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getPoCode() {
        return poCode;
    }

    public void setPoCode(String poCode) {
        this.poCode = poCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getQuantityWithComma(){
        return NumberWithComma.addComma(quantity);
    }

    public String getPricePerUnitWithComma(){
        return NumberWithComma.addComma(pricePerUnit);
    }

}
