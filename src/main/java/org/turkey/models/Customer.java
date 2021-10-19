package org.turkey.models;

import java.math.BigInteger;

public class Customer extends Information{
    private String address;

    public Customer(BigInteger id,String name, String phoneNo, String address){
        super(id, name, phoneNo);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
