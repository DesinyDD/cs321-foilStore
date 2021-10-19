package org.turkey.models;

import java.math.BigInteger;

public class PurchaseOrderLine extends OrderLine{
    private float pricePerUnit;

    public PurchaseOrderLine(BigInteger id, String code, String colorCode, BigInteger quantity, float pricePerUnit) {
        super(id, code, colorCode, quantity);
        this.pricePerUnit = pricePerUnit;
    }
}
