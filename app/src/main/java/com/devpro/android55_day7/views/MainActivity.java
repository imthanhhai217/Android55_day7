package com.devpro.android55_day7.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.devpro.android55_day7.R;
import com.devpro.android55_day7.interfaces.GetAllProductView;
import com.devpro.android55_day7.models.categories.CategoryModel;
import com.devpro.android55_day7.models.categories.CategoryObj;
import com.devpro.android55_day7.models.products.AllProductResponse;
import com.devpro.android55_day7.networks.DummyService;
import com.devpro.android55_day7.presenters.DummyPresenter;
import com.devpro.android55_day7.utils.ApiState;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetAllProductView {
    private static final String TAG = "MainActivity";
    private DummyService dummyService;
    private EditText edtSearch;
    private Button btnSearch;
    private DummyPresenter dummyPresenter;
    private ApiState apiStateAllProduct = ApiState.NONE;
    private ApiState apiStateAllCategory = ApiState.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dummyPresenter = new DummyPresenter();
        dummyPresenter.setGetAllProductViewCallback(this);
        initView();
       }

    private void initView() {
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(v -> {
            String keywords = edtSearch.getText().toString();
            if (keywords != null && !keywords.equals("")){
                dummyPresenter.searchProduct(keywords);
            }
        });


        dummyPresenter.getAllProduct();
        apiStateAllProduct = ApiState.CALLED;
        dummyPresenter.getAllCategories();
        apiStateAllCategory = ApiState.CALLED;
    }

    @Override
    public void onGetAllProductSuccess(AllProductResponse response) {
        apiStateAllProduct = ApiState.SUCCESS;
        Log.d(TAG, "onGetAllProductSuccess: "+response.getProducts().get(0).toString());
        dummyPresenter.filterProductByCategory(apiStateAllProduct,apiStateAllCategory);
    }

    @Override
    public void onGetAllProductFailed(String message) {
        apiStateAllProduct = ApiState.FAILED;
        Log.d(TAG, "onGetAllProductFailed: "+message);
        dummyPresenter.filterProductByCategory(apiStateAllProduct,apiStateAllCategory);
    }

    @Override
    public void onAllCategorySuccess(ArrayList<CategoryObj> response) {
        apiStateAllCategory = ApiState.SUCCESS;
        Log.d(TAG, "onAllCategorySuccess: "+response.size());
        dummyPresenter.filterProductByCategory(apiStateAllProduct,apiStateAllCategory);
    }

    @Override
    public void onAllCategoryFailed(String message) {
        apiStateAllCategory = ApiState.FAILED;
        Log.d(TAG, "onAllCategoryFailed: "+message);
        dummyPresenter.filterProductByCategory(apiStateAllProduct,apiStateAllCategory);
    }

    @Override
    public void onResponseListCategoryModel(ArrayList<CategoryModel> data) {
        Log.d(TAG, "onResponseListCategoryModel: "+data.toString());
    }

    @Override
    public void onSearchProductSuccess(AllProductResponse response) {
        Log.d(TAG, "onSearchProductSuccess: "+response.getProducts().get(0).toString());
    }

    @Override
    public void onSearchProductFailed(String message) {
        Log.d(TAG, "onSearchProductFailed: "+message);
    }
}