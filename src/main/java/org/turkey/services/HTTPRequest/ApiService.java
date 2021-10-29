package org.turkey.services.HTTPRequest;

import org.turkey.models.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.math.BigInteger;
import java.util.List;

public interface ApiService {
    @GET("items")
    Call<List<Item>> listItems();

    @GET("items/{code}")
    Call<Item> showItem(@Path("code") String code);

    @POST("items")
    Call<ResponseMessage> createItem(@Body Item item);

    @GET("customers")
    Call<List<Customer>> listCustomers();

    @GET("sale_order")
    Call<List<SaleOrder>> listSaleOrder();

    @GET("po")
    Call<List<Po>> listPo();

    @GET("supplier")
    Call<List<Supplier>> listSupplier();

    @POST("sale_order")
    Call<ResponseMessage> createSaleOrder(@Body SaleOrder saleOrder);

    @POST("po")
    Call<ResponseMessage> createPO(@Body Po po);

    @POST("po/to_wait_pay/{code}")
    Call<Object> poToWaitPay(@Path("code") String code);

    @POST("po/to_complete/{code}")
    Call<Object> poToComplete(@Path("code") String code);

    @POST("sale_order/to_complete/{code}")
    Call<Object> saleOrderToComplete(@Path("code") String code);

    @POST("customers")
    Call<Object> createCustomer(@Body Customer customer);

    @PUT("customers/{id}")
    Call<Object> updateCustomer(@Body Customer customer, @Path("id") BigInteger id);

    @PUT("items/{code}")
    Call<ResponseMessage> updateItem(@Body Item item, @Path("code") String code);

    @GET("report")
    Call<List<SaleOrder>> getReport();
}
