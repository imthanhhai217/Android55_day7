package com.devpro.android55_day7.interfaces;

import com.devpro.android55_day7.models.categories.CategoryModel;
import com.devpro.android55_day7.models.categories.CategoryObj;
import com.devpro.android55_day7.models.products.AllProductResponse;

import java.util.ArrayList;

public interface GetAllProductView {
    void onGetAllProductSuccess(AllProductResponse response);

    void onGetAllProductFailed(String message);

    void onSearchProductSuccess(AllProductResponse response);

    void onSearchProductFailed(String message);

    void onAllCategorySuccess(ArrayList<CategoryObj> response);

    void onAllCategoryFailed(String message);

    void onResponseListCategoryModel(ArrayList<CategoryModel> data);
}
