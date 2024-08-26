package com.devpro.android55_day7.presenters;

import com.devpro.android55_day7.interactors.DummyInteractor;
import com.devpro.android55_day7.interfaces.DummyPresenterImpl;
import com.devpro.android55_day7.interfaces.GetAllProductView;
import com.devpro.android55_day7.models.categories.CategoryModel;
import com.devpro.android55_day7.models.categories.CategoryObj;
import com.devpro.android55_day7.models.products.AllProductResponse;
import com.devpro.android55_day7.models.products.Product;
import com.devpro.android55_day7.utils.ApiState;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DummyPresenter implements DummyPresenterImpl {
    private GetAllProductView getAllProductViewCallback;
    private DummyInteractor dummyInteractor;

    public DummyPresenter() {
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
            if (response.isSuccessful()) {
                if (response.code() == 200) {
                    listProducts = response.body().getProducts();
                    getAllProductViewCallback.onGetAllProductSuccess(response.body());
                } else {
                    getAllProductViewCallback.onGetAllProductFailed("Error code: " + response.code());
                }
            }
        }

        @Override
        public void onFailure(Call<AllProductResponse> call, Throwable throwable) {
            getAllProductViewCallback.onGetAllProductFailed("Error " + throwable.getMessage());
        }
    };

    @Override
    public void searchProduct(String keywords) {
        dummyInteractor.setSearchProductResponseCallback(searchProductResponseCallback);
        dummyInteractor.searchProduct(keywords);
    }

    private ArrayList<CategoryObj> listCategory;
    private List<Product> listProducts;
    private ArrayList<CategoryModel> listCategoryModels;

    public void filterProductByCategory(ApiState apiStateAllProduct, ApiState apiStateAllCategory) {
        if (apiStateAllProduct == ApiState.SUCCESS && apiStateAllCategory == ApiState.SUCCESS) {
            //Filter
            listCategoryModels = new ArrayList<>();
            listCategory.stream().forEach(categoryObj -> {
                List<Product> filterResult = listProducts.stream()
                        .filter(product -> categoryObj.getName().equalsIgnoreCase(product.getCategory()))
                        .collect(Collectors.toList());

                Collections.shuffle(filterResult);

                CategoryModel categoryChild = new CategoryModel();
                categoryChild.setCategoryName(categoryObj.getName());
                categoryChild.setCategorySize(filterResult.size());
                categoryChild.setUrl(categoryObj.getUrl());

                ArrayList<String> imageData = new ArrayList<>();
                int listSize = 4;
                if (filterResult.size() < 4){
                    listSize = filterResult.size();
                }
                for (int i = 0; i < listSize; i++) {
                    imageData.add(filterResult.get(i).getThumbnail());
                }
                categoryChild.setProductDemo(imageData);

                listCategoryModels.add(categoryChild);
            });
            getAllProductViewCallback.onResponseListCategoryModel(listCategoryModels);
        }
    }

    private Callback<AllProductResponse> searchProductResponseCallback = new Callback<AllProductResponse>() {
        @Override
        public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
            if (response.isSuccessful()) {
                if (response.code() == 200) {
                    getAllProductViewCallback.onSearchProductSuccess(response.body());
                } else {
                    getAllProductViewCallback.onSearchProductFailed("Error code: " + response.code());
                }
            }
        }

        @Override
        public void onFailure(Call<AllProductResponse> call, Throwable throwable) {
            getAllProductViewCallback.onSearchProductFailed("Error " + throwable.getMessage());
        }
    };

    @Override
    public void getAllCategories() {
        dummyInteractor.setGetAllCategoryCallback(getAllCategoryResponseCallback);
        dummyInteractor.getAllCategory();
    }

    private Callback<JsonArray> getAllCategoryResponseCallback = new Callback<JsonArray>() {
        @Override
        public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
            if (response.isSuccessful()) {
                if (response.code() == 200) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<ArrayList<CategoryObj>>() {
                    }.getType();
                    ArrayList<CategoryObj> listData = gson.fromJson(response.body(), listType);
                    listCategory = listData;
                    getAllProductViewCallback.onAllCategorySuccess(listData);
                } else {
                    getAllProductViewCallback.onAllCategoryFailed("Error code: " + response.code());
                }
            }
        }

        @Override
        public void onFailure(Call<JsonArray> call, Throwable throwable) {
            getAllProductViewCallback.onAllCategoryFailed("Error " + throwable.getMessage());
        }
    };

}
