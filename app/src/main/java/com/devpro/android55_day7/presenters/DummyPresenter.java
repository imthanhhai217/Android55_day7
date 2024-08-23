package com.devpro.android55_day7.presenters;

import com.devpro.android55_day7.interactors.DummyInteractor;
import com.devpro.android55_day7.interfaces.DummyPresenterImpl;
import com.devpro.android55_day7.interfaces.GetAllProductView;
import com.devpro.android55_day7.models.AllProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DummyPresenter implements DummyPresenterImpl {
    private GetAllProductView getAllProductViewCallback;
    private DummyInteractor dummyInteractor;

    public DummyPresenter(){
        dummyInteractor = DummyInteractor.getInstances();
    }

    public void setGetAllProductViewCallback(GetAllProductView getAllProductViewCallback) {
        this.getAllProductViewCallback = getAllProductViewCallback;
    }

    @Override
    public void getAllProduct() {
        dummyInteractor.setGetAllProductResponseCallback(allProductResponseCallback);
        dummyInteractor.getAllProduct();
    }
    private Callback<AllProductResponse> allProductResponseCallback = new Callback<AllProductResponse>() {
        @Override
        public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
            if (response.isSuccessful()){
                if (response.code() == 200){
                    getAllProductViewCallback.onGetAllProductSuccess(response.body());
                }else {
                    getAllProductViewCallback.onGetAllProductFailed("Error code: "+response.code());
                }
            }
        }

        @Override
        public void onFailure(Call<AllProductResponse> call, Throwable throwable) {
            getAllProductViewCallback.onGetAllProductFailed("Error "+throwable.getMessage());
        }
    };

    @Override
    public void searchProduct(String keywords) {
        dummyInteractor.setSearchProductResponseCallback(searchProductResponseCallback);
        dummyInteractor.searchProduct(keywords);
    }

    @Override
    public void getAllCategories() {

    }

    private Callback<AllProductResponse> searchProductResponseCallback = new Callback<AllProductResponse>() {
        @Override
        public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
            if (response.isSuccessful()){
                if (response.code() == 200){
                    getAllProductViewCallback.onSearchProductSuccess(response.body());
                }else {
                    getAllProductViewCallback.onSearchProductFailed("Error code: "+response.code());
                }
            }
        }

        @Override
        public void onFailure(Call<AllProductResponse> call, Throwable throwable) {
            getAllProductViewCallback.onSearchProductFailed("Error "+throwable.getMessage());
        }
    };
}
