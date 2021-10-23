package org.turkey.models;

import java.math.BigInteger;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Po {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("supplier_id")
    @Expose
    private BigInteger supplierId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("total_price")
    @Expose
    private float totalPrice;
    @SerializedName("po_lines")
    @Expose
    private List<PoLine> poLines = null;
    @SerializedName("supplier")
    @Expose
    private Supplier supplier;

    /**
     * No args constructor for use in serialization
     *
     */
    public Po() {
    }

    /**
     *  @param code
     * @param supplierId
     * @param status
     * @param totalPrice
     * @param poLines
     * @param supplier
     */
    public Po(String code, BigInteger supplierId, String status, float totalPrice, List<PoLine> poLines, Supplier supplier) {
        super();
        this.code = code;
        this.supplierId = supplierId;
        this.status = status;
        this.totalPrice = totalPrice;
        this.poLines = poLines;
        this.supplier = supplier;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigInteger getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(BigInteger supplierId) {
        this.supplierId = supplierId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PoLine> getPoLines() {
        return poLines;
    }

    public void setPoLines(List<PoLine> poLines) {
        this.poLines = poLines;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Po{" +
                "code='" + code + '\'' +
                ", supplierId=" + supplierId +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", poLines=" + poLines +
                ", supplier=" + supplier +
                '}';
    }
}
