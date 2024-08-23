package com.devpro.android55_day7.interfaces;

import com.devpro.android55_day7.models.AllProductResponse;

public interface GetAllProductView {
    void onGetAllProductSuccess(AllProductResponse response);

    void onGetAllProductFailed(String message);

    void onSearchProductSuccess(AllProductResponse response);

    void onSearchProductFailed(String message);
}
