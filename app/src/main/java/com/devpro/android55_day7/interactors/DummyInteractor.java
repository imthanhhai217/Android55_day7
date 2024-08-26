package com.devpro.android55_day7.interactors;

import com.devpro.android55_day7.interfaces.DummyInteractorImpl;
import com.devpro.android55_day7.models.products.AllProductResponse;
import com.devpro.android55_day7.networks.DummyService;
import com.devpro.android55_day7.networks.RetrofitClient;
import com.google.gson.JsonArray;

import retrofit2.Callback;

public class DummyInteractor implements DummyInteractorImpl {
    private static DummyInteractor instances;
    private Callback<AllProductResponse> getAllProductResponseCallback;
    private Callback<AllProductResponse> searchProductResponseCallback;
    private Callback<JsonArray> getAllCategoryResponseCallback;
    private static DummyService dummyService;

    public void setGetAllProductResponseCallback(Callback<AllProductResponse> getAllProductResponseCallback) {
        this.getAllProductResponseCallback = getAllProductResponseCallback;
    }

    public void setSearchProductResponseCallback(Callback<AllProductResponse> searchProductResponseCallback) {
        this.searchProductResponseCallback = searchProductResponseCallback;
    }

    public static DummyInteractor getInstances() {
        if (instances == null){
            instances = new DummyInteractor();
        }
        dummyService = RetrofitClient.getDummyService();
        return instances;
    }

    @Override
    public void getAllProduct() {
        dummyService.getAllProduct().enqueue(getAllProductResponseCallback);
    }

    @Override
    public void searchProduct(String keywords) {
        dummyService.searchProduct(keywords).enqueue(searchProductResponseCallback);
    }

    @Override
    public void getAllCategory() {
        dummyService.getAllCategory().enqueue(getAllCategoryResponseCallback);
    }

    public void setGetAllCategoryCallback(Callback<JsonArray> getAllCategoryResponseCallback) {
        this.getAllCategoryResponseCallback = getAllCategoryResponseCallback;
    }
}
