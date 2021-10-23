package org.turkey.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class SaleOrderLine {

    @SerializedName("id")
    @Expose
    private BigInteger id;
    @SerializedName("sale_order_code")
    @Expose
    private String saleOrderCode;
    @SerializedName("color_code")
    @Expose
    private String colorCode;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("item")
    @Expose
    private Item item;

    /**
     * No args constructor for use in serialization
     *
     */
    public SaleOrderLine() {
    }

    /**
     *
     * @param saleOrderCode
     * @param item
     * @param quantity
     * @param colorCode
     * @param id
     */
    public SaleOrderLine(BigInteger id, String saleOrderCode, String colorCode, Integer quantity, Item item) {
        super();
        this.id = id;
        this.saleOrderCode = saleOrderCode;
        this.colorCode = colorCode;
        this.quantity = quantity;
        this.item = item;
    }
    public SaleOrderLine(String saleOrderCode, String colorCode, Integer quantity, Item item) {
        this.saleOrderCode = saleOrderCode;
        this.colorCode = colorCode;
        this.quantity = quantity;
        this.item = item;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getSaleOrderCode() {
        return saleOrderCode;
    }

    public void setSaleOrderCode(String saleOrderCode) {
        this.saleOrderCode = saleOrderCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "SaleOrderLine{" +
                "id=" + id +
                ", saleOrderCode='" + saleOrderCode + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", quantity=" + quantity +
                ", item=" + item +
                '}';
    }
}
