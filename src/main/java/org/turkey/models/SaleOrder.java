package org.turkey.models;

import java.math.BigInteger;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleOrder {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("customer_id")
    @Expose
    private BigInteger customerId;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("total_price")
    @Expose
    private Double totalPrice;
    @SerializedName("complete_date")
    @Expose
    private String completeDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("sale_order_lines")
    @Expose
    private List<SaleOrderLine> saleOrderLines = null;
    @SerializedName("customer")
    @Expose
    private Customer customer;

    /**
     * No args constructor for use in serialization
     *
     */
    public SaleOrder() {
    }

    /**
     *
     * @param createdAt
     * @param saleOrderLines
     * @param code
     * @param totalPrice
     * @param customerId
     * @param status
     * @param completeDate
     * @param updatedAt
     * @param customer
     */
    public SaleOrder(String code, BigInteger customerId, Status status, Double totalPrice, String completeDate, String createdAt, String updatedAt, List<SaleOrderLine> saleOrderLines, Customer customer) {
        super();
        this.code = code;
        this.customerId = customerId;
        this.status = status;
        this.totalPrice = totalPrice;
        this.completeDate = completeDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.saleOrderLines = saleOrderLines;
        this.customer = customer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigInteger getCustomerId() {
        return customerId;
    }

    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<SaleOrderLine> getSaleOrderLines() {
        return saleOrderLines;
    }

    public void setSaleOrderLines(List<SaleOrderLine> saleOrderLines) {
        this.saleOrderLines = saleOrderLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerName(){return customer.getName();}

    @Override
    public String toString() {
        return "SaleOrder{" +
                "code='" + code + '\'' +
                ", customerId=" + customerId +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", completeDate='" + completeDate + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", saleOrderLines=" + saleOrderLines +
                ", customer=" + customer +
                '}';
    }
}