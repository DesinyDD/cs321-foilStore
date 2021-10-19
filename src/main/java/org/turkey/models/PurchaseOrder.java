package org.turkey.models;

import java.util.ArrayList;

public class PurchaseOrder extends Order{
    private Enum<PurchaseStatus> status;

    public PurchaseOrder(String code, Information partner, float totalPrice, Enum<PurchaseStatus> status) {
        super(code, partner, totalPrice);
        this.status = status;
    }

    public Enum<PurchaseStatus> getStatus() {return status;}
}
