package org.turkey.models;

import java.math.BigInteger;
import java.util.ArrayList;
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
    private PurchaseStatus status;
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
    public Po(String code, BigInteger supplierId, PurchaseStatus status, float totalPrice, List<PoLine> poLines, Supplier supplier) {
        super();
        this.code = code;
        this.supplierId = supplierId;
        this.status = status;
        this.totalPrice = totalPrice;
        this.poLines = poLines;
        this.supplier = supplier;
    }

    public Po(String code, BigInteger supplierId, float totalPrice, Supplier supplier) {
        this.code = code;
        this.supplierId = supplierId;
        this.status = PurchaseStatus.Wait;
        this.totalPrice = totalPrice;
        this.poLines = new ArrayList<>();
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

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
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

    public String getSupplierName() {return supplier.getName();}

    public void addTotalPrice(float price) { this.totalPrice += price;}

    public void addPoLine(PoLine poLine) {this.poLines.add(poLine);}

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

    public String showLabel(){
        String order="";
        for (PoLine poLine : poLines){
            order = order+poLine.getColorCode()+" ราคา "+poLine.getPricePerUnit()+" /ม้วน จำนวน"+poLine.getQuantity()+" ม้วน\n";
        }
        return "รหัสใบสั่งขาย : "+code+"\n"+
                "บริษัทที่สั่งซื้อ : "+ supplier.getName() +"\n"+
                "รายการที่สั่งซื้อ\n" + order + "ยอดรวม : " + totalPrice+" บาท";
    }
}
