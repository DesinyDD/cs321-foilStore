package org.turkey.services.HTTPRequest;

import com.google.gson.Gson;
import org.turkey.models.*;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class DBConnector {

    private static String API_ENDPOINT = "http://localhost:8000/api/";

    public ApiService startConnection() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        return service;
    }

    public List<Item> getItem() {
        ApiService service = startConnection();
        Call<List<Item>> CallItem = service.listItems();
        try {
            List<Item> ItemList = CallItem.execute().body();
            return ItemList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<SaleOrder> getSaleOrder() {
        ApiService service = startConnection();
        Call<List<SaleOrder>> callSaleOrder = service.listSaleOrder();
        try {
            List<SaleOrder> saleOrderList = callSaleOrder.execute().body();
            return saleOrderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    public List<SaleOrder> getSaleOrder() {
//        ApiService service = startConnection();
//        Call<List<SaleOrder>> callSaleOrder = service.listSaleOrder();
//        List<SaleOrder> saleOrderList = new ArrayList<>();
//        callSaleOrder.enqueue(new Callback<List<SaleOrder>>() {
//            @Override
//            public void onResponse(Call<List<SaleOrder>> call, Response<List<SaleOrder>> response) {
//                if (response.isSuccessful()) {
//                    saleOrderList.addAll(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<SaleOrder>> call, Throwable t) {
//
//            }
//        });
//        return saleOrderList;
//    }

    public List<Po> getPO() {
        ApiService service = startConnection();
        Call<List<Po>> callPO = service.listPo();
        try {
            List<Po> pos = callPO.execute().body();
            return pos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> getCustomer() {
        ApiService service = startConnection();
        Call<List<Customer>> callCustomer = service.listCustomers();
        try {
            List<Customer> customers = callCustomer.execute().body();
            return customers;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Supplier> getSupplier() {
        ApiService service = startConnection();
        Call<List<Supplier>> callSupplier = service.listSupplier();
        try {
            List<Supplier> suppliers = callSupplier.execute().body();
            return suppliers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createSaleOrder(SaleOrder saleOrder) {
        ApiService service = startConnection();
        Call<Object> callSaleOrder = service.createSaleOrder(saleOrder);

        try {
            String res = new Gson().toJson(callSaleOrder.execute().body());
            System.out.println("IN CREATE SO");
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createPO(Po po) {
        ApiService service = startConnection();
        Call<Object> callPo = service.createPO(po);

        try {
            String res = new Gson().toJson(callPo.execute().body());
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void poToWaitPay(Po po) {
        ApiService service = startConnection();
        Call<Object> callToWaitPay = service.poToWaitPay(po.getCode());

        try {
            String res = new Gson().toJson(callToWaitPay.execute().body());
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void poToComplete(Po po) {
        ApiService service = startConnection();
        Call<Object> callToComplete = service.poToComplete(po.getCode());

        try {
            String res = new Gson().toJson(callToComplete.execute().body());
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saleOrderToComplete(SaleOrder saleOrder) {
        ApiService service = startConnection();
        Call<Object> callToComplete = service.saleOrderToComplete(saleOrder.getCode());

        try {
            String res = new Gson().toJson(callToComplete.execute().body());
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createCustomer(Customer customer) {
        ApiService service = startConnection();
        Call<Object> callCreateCustomer = service.createCustomer(customer);

        try {
            String res = new Gson().toJson(callCreateCustomer.execute().body());
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer) {
        ApiService service = startConnection();
        Call<Object> callUpdateCustomer = service.updateCustomer(customer, customer.getCustomerId());

        try {
            String res = new Gson().toJson(callUpdateCustomer.execute().body());
            System.out.println("TO COMPLETE DBCONNECTOR");
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(Item item, Item baseItem) {
        ApiService service = startConnection();
        Call<Object> callUpdateItem = service.updateItem(item, baseItem.getCode());

        try {
            String res = new Gson().toJson(callUpdateItem.execute().body());
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createItem(Item item) {
        ApiService service = startConnection();
        Call<Object> callCreateItem = service.createItem(item);

        try {
            String res = new Gson().toJson(callCreateItem.execute().body());
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
