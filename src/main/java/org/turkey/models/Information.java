package org.turkey.models;

import java.math.BigInteger;

public abstract class Information {
    private BigInteger id;
    private String name;
    private String phoneNo;

    public Information(BigInteger id, String name, String phoneNo){
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
