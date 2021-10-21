package org.turkey.services.HTTPRequest;

import org.turkey.models.Customer;
import org.turkey.models.Item;
import org.turkey.models.Po;
import org.turkey.models.SaleOrder;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {
    @GET("items")
    Call<List<Item>> listItems();

    @GET("items/{color_code}")
    Call<Item> showItem(@Path("color_code") String color_code);

    @POST("items")
    Call<Object> createItem(@Body Item item);

    @GET("customers")
    Call<List<Customer>> listCustomers();

    @GET("sale_order")
    Call<List<SaleOrder>> listSaleOrder();

    @GET("po")
    Call<List<Po>> listPo();
}
