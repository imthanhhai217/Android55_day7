package com.devpro.android55_day7.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.devpro.android55_day7.R;
import com.devpro.android55_day7.interfaces.GetAllProductView;
import com.devpro.android55_day7.models.AllProductResponse;
import com.devpro.android55_day7.networks.DummyService;
import com.devpro.android55_day7.networks.RetrofitClient;
import com.devpro.android55_day7.presenters.DummyPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GetAllProductView {
    private static final String TAG = "MainActivity";
    private DummyService dummyService;
    private EditText edtSearch;
    private Button btnSearch;
    private DummyPresenter dummyPresenter;

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
        dummyPresenter.getAllCategories();
    }

    @Override
    public void onGetAllProductSuccess(AllProductResponse response) {
        Log.d(TAG, "onGetAllProductSuccess: "+response.getProducts().get(0).toString());
    }

    @Override
    public void onGetAllProductFailed(String message) {
        Log.d(TAG, "onGetAllProductFailed: "+message);
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