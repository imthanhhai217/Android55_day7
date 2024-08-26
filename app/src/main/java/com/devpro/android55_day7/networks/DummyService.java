package com.devpro.android55_day7.networks;

import com.devpro.android55_day7.constants.ConstantApi;
import com.devpro.android55_day7.models.products.AllProductResponse;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DummyService {
    @GET(ConstantApi.GET_ALL_PRODUCT)
    Call<AllProductResponse> getAllProduct();
    @GET(ConstantApi.GET_SEARCH_PRODUCT)
    Call<AllProductResponse> searchProduct(@Query("q") String productName);
    @GET(ConstantApi.GET_ALL_CATEGORY)
    Call<JsonArray> getAllCategory();
}
