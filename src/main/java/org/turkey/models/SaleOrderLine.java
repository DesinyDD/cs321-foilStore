package org.turkey.models;

import java.math.BigInteger;

public class SaleOrderLine extends OrderLine{

    public SaleOrderLine(BigInteger id, String code, String colorCode, BigInteger quantity) {
        super(id, code, colorCode, quantity);
    }
}
