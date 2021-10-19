package org.turkey.models;

import java.util.ArrayList;
import java.util.Date;

public class SaleOrder extends Order{
    private Enum<SaleStatus> status;
    private Date completeDate;

    public SaleOrder(String code, Information partner, float totalPrice, Enum<SaleStatus> status) {
        super(code, partner, totalPrice);
        this.status = status;
    }

    public Enum<SaleStatus> getStatus() {return status;}

    public void setCompleteDate(Date completeDate) {this.completeDate = completeDate;}

    public void setStatus(Enum<SaleStatus> status) {this.status = status;}
}
