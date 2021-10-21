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
     *
     * @param code
     * @param supplierId
     * @param poLines
     * @param supplier
     * @param status
     */
    public Po(String code, BigInteger supplierId, String status, List<PoLine> poLines, Supplier supplier) {
        super();
        this.code = code;
        this.supplierId = supplierId;
        this.status = status;
        this.poLines = poLines;
        this.supplier = supplier;
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

}