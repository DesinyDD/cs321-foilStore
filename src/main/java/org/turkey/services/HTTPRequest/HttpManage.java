package org.turkey.services.HTTPRequest;

import org.turkey.models.Item;
import org.turkey.models.Po;
import org.turkey.models.SaleOrder;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class HttpManage {

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
        List<SaleOrder> saleOrderList = null;
        try {
            saleOrderList = callSaleOrder.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        List<SaleOrder> saleOrderList = new ArrayList<>();
//        callSaleOrder.enqueue(new Callback<List<SaleOrder>>() {
//            @Override
//            public void onResponse(Call<List<SaleOrder>> call, Response<List<SaleOrder>> response) {
//                if (response.isSuccessful()) {
//                    saleOrderList.addAll(response.body());
//                    System.out.println("IN HTTP" + saleOrderList);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<SaleOrder>> call, Throwable t) {
//
//            }
//        });
        return saleOrderList;
    }

    public List<Po> getPo() {
        ApiService apiService = startConnection();
        Call<List<Po>> callPo = apiService.listPo();
        try {
            List<Po> pos = callPo.execute().body();
            System.out.println("RETURN " + pos.toString() );
            return pos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
