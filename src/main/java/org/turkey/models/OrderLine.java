package org.turkey.models;

import java.math.BigInteger;

public abstract class OrderLine {
    private BigInteger id;
    private String code;
    private String colorCode;
    private BigInteger quantity;

    public OrderLine(BigInteger id, String code, String colorCode, BigInteger quantity){
        this.id = id;
        this.code = code;
        this.colorCode = colorCode;
        this.quantity = quantity;
    }
}
