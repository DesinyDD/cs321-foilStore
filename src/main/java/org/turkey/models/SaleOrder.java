package org.turkey.models;

import java.util.ArrayList;
import java.util.Date;

public class SaleOrder extends Order{
    private ArrayList<SaleOrderLine> list;
    private Enum<SaleStatus> status;
    private Date completeDate;
}
