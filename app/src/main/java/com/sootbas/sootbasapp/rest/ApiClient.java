package com.sootbas.sootbasapp.rest;

import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.common.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // example url: https://itunes.apple.com/search?term=podcasts&genreId=1406&limit=10
    private static Retrofit sRetrofit;

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .client(Utils.getsOkHttpClient())
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }


}
