package org.turkey.services;

import org.turkey.models.*;

import java.math.BigInteger;
import java.util.ArrayList;

public class MockUpData {
    public static void mockUpStock(ArrayList<Item> stock){
//        Item item = new Item("OSP 900", new BigInteger(230+""), 1000, new BigInteger(50+""));
//        stock.add(item);
//        item = new Item("OSP 730", new BigInteger(180+""), 1000, new BigInteger(35+""));
//        stock.add(item);
    }
    public static void mockUpCustomer(ArrayList<Customer> customers){
        Customer customer = new Customer(new BigInteger("1"),"Ford","Bodin", "08xxxxxxxx");
        customers.add(customer);
        customer = new Customer(new BigInteger("2"),"MIX", "KU","08xxxxxxxx");
        customers.add(customer);
        customer = new Customer(new BigInteger("3"),"Q","Barn", "08xxxxxxxx");
        customers.add(customer);
    }
    public static void mockUpSupplier(){

    }
    public static void mockUpSO(ArrayList<Order> orders){
//        Order order = new SaleOrder("F62190158900",new Customer(new BigInteger("6"),"Test","09xxxxxxxx","?"),8000, Status.WaitCreateBill);
//        OrderLine orderLine = new SaleOrderLine(new BigInteger("1"),"F62190158900","OSP 730", new BigInteger("35"));
//        order.addOrderLine(orderLine);
//        orderLine = new SaleOrderLine(new BigInteger("2"),"F62190158900","OSP 900", new BigInteger("12"));
//        order.addOrderLine(orderLine);
//        orderLine = new SaleOrderLine(new BigInteger("3"),"F62190158900","OSP 830", new BigInteger("5"));
//        order.addOrderLine(orderLine);
//        orders.add(order);
//        order = new SaleOrder("F62190158901",new Customer(new BigInteger("6"),"Test","09xxxxxxxx","?"),13500, Status.WaitPay);
//        orderLine = new SaleOrderLine(new BigInteger("4"),"F62190158901","OSP 420", new BigInteger("15"));
//        order.addOrderLine(orderLine);
//        orderLine = new SaleOrderLine(new BigInteger("5"),"F62190158901","OSP 630", new BigInteger("5"));
//        order.addOrderLine(orderLine);
//        orderLine = new SaleOrderLine(new BigInteger("6"),"F62190158901","OSP 213", new BigInteger("75"));
//        order.addOrderLine(orderLine);
//        orders.add(order);
//        order = new SaleOrder("F62190158899",new Customer(new BigInteger("6"),"Test","09xxxxxxxx","?"),8000, Status.Complete);
//        orderLine = new SaleOrderLine(new BigInteger("7"),"F62190158900","OSP 730", new BigInteger("35"));
//        order.addOrderLine(orderLine);
//        orderLine = new SaleOrderLine(new BigInteger("8"),"F62190158900","OSP 900", new BigInteger("12"));
//        order.addOrderLine(orderLine);
//        orderLine = new SaleOrderLine(new BigInteger("9"),"F62190158900","OSP 830", new BigInteger("5"));
//        order.addOrderLine(orderLine);
//        orders.add(order);
    }
    public static void mockUpPO(ArrayList<Order> orders){
//        Order order = new PurchaseOrder("F62190158900",new Customer(new BigInteger("6"),"Test","09xxxxxxxx","?"),8000, PurchaseStatus.Wait);
//        OrderLine orderLine = new PurchaseOrderLine(new BigInteger("1"),"F62190158900","OSP 730", new BigInteger("35"),900);
//        order.addOrderLine(orderLine);
//        orderLine = new PurchaseOrderLine(new BigInteger("2"),"F62190158900","OSP 900", new BigInteger("12"),900);
//        order.addOrderLine(orderLine);
//        orderLine = new PurchaseOrderLine(new BigInteger("3"),"F62190158900","OSP 830", new BigInteger("5"),900);
//        order.addOrderLine(orderLine);
//        orders.add(order);
//        order = new PurchaseOrder("F62190158901",new Customer(new BigInteger("6"),"Test","09xxxxxxxx","?"),13500, PurchaseStatus.WaitPay);
//        orderLine = new PurchaseOrderLine(new BigInteger("4"),"F62190158901","OSP 420", new BigInteger("15"),900);
//        order.addOrderLine(orderLine);
//        orderLine = new PurchaseOrderLine(new BigInteger("5"),"F62190158901","OSP 630", new BigInteger("5"),900);
//        order.addOrderLine(orderLine);
//        orderLine = new PurchaseOrderLine(new BigInteger("6"),"F62190158901","OSP 213", new BigInteger("75"),900);
//        order.addOrderLine(orderLine);
//        orders.add(order);
//        order = new PurchaseOrder("F62190158899",new Customer(new BigInteger("6"),"Test","09xxxxxxxx","?"),8000, PurchaseStatus.Complete);
//        orderLine = new PurchaseOrderLine(new BigInteger("7"),"F62190158900","OSP 730", new BigInteger("35"),900);
//        order.addOrderLine(orderLine);
//        orderLine = new PurchaseOrderLine(new BigInteger("8"),"F62190158900","OSP 900", new BigInteger("12"),900);
//        order.addOrderLine(orderLine);
//        orderLine = new PurchaseOrderLine(new BigInteger("9"),"F62190158900","OSP 830", new BigInteger("5"),900);
//        order.addOrderLine(orderLine);
//        orders.add(order);
    }
}
