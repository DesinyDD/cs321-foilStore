package org.turkey.models;

import java.util.ArrayList;

public class PurchaseOrder extends Order{
    private ArrayList<PurchaseOrderLine> list;
    private Enum<PurchaseStatus> status;
}
