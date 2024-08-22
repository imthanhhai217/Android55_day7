package com.devpro.android55_day7;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.devpro.android55_day7.models.AllProductResponse;
import com.devpro.android55_day7.networks.DummyService;
import com.devpro.android55_day7.networks.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DummyService dummyService;
    private EditText edtSearch;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dummyService = RetrofitClient.getDummyService();
        initView();
       }

    private void initView() {
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(v -> {
            String keywords = edtSearch.getText().toString();
            if (keywords != null && !keywords.equals("")){
                searchProduct(keywords);
            }
        });
        dummyService.getAllProduct().enqueue(new Callback<AllProductResponse>() {
            @Override
            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200){
                        Log.d(TAG, "onResponse: "+response.body().getProducts().get(0).toString());
                    }else {
                        Log.d(TAG, "onResponse: "+response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProductResponse> call, Throwable throwable) {
                Log.d(TAG, "onFailure: "+throwable.getMessage());
            }
        });
    }

    private void searchProduct(String keywords) {
        dummyService.searchProduct(keywords).enqueue(new Callback<AllProductResponse>() {
            @Override
            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200){
                        Log.d(TAG, "searchProduct onResponse: "+response.body().getProducts().get(0).toString());
                    }else {
                        Log.d(TAG, "searchProduct onResponse: "+response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProductResponse> call, Throwable throwable) {
                Log.d(TAG, "searchProduct onFailure: "+throwable.getMessage());
            }
        });
    }
}