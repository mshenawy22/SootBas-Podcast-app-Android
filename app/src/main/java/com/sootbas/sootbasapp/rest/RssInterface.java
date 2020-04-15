package com.sootbas.sootbasapp.rest;


import com.sootbas.sootbasapp.model.episode.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RssInterface {

//    @GET("{path}")
//    Call<Feed> getItems(@Path("path") String feed);

    @GET
    Call<Feed> getItems(@Url String url);


}
