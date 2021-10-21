package org.turkey.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleOrderLine {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sale_order_code")
    @Expose
    private String saleOrderCode;
    @SerializedName("color_code")
    @Expose
    private String colorCode;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    /**
     * No args constructor for use in serialization
     *
     */
    public SaleOrderLine() {
    }

    /**
     *
     * @param saleOrderCode
     * @param quantity
     * @param colorCode
     * @param id
     */
    public SaleOrderLine(Integer id, String saleOrderCode, String colorCode, Integer quantity) {
        super();
        this.id = id;
        this.saleOrderCode = saleOrderCode;
        this.colorCode = colorCode;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

}
