package com.devpro.android55_day7.networks;

import com.devpro.android55_day7.constants.ConstantApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit instances;

    private static Retrofit getInstances() {
        if (instances == null) {
            // Create retrofit instance
            instances = new Retrofit.Builder()
                    .baseUrl(ConstantApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instances;
    }

    public static DummyService getDummyService(){
        return getInstances().create(DummyService.class);
    }
}
