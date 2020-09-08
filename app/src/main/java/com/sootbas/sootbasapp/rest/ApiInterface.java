package com.sootbas.sootbasapp.rest;


import com.google.auto.value.AutoValue;
import com.sootbas.sootbasapp.model.podcast.Results;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    // download the podcasts for the genreId
    // example url: https://itunes.apple.com/search?term=podcasts&genreId=1406&limit=10&sort=recent
//    @GET("search?")

    @GET("/channels/{genreId}")
    Call<Results> getGenrePodcasts(

//                                   @Query("term") String term,
//                                  @Query("genreId") int genreId);
                                  @Path("genreId") int genreId);
//                                   @Query("limit") int limit,
//                                   @Query("sort") String sort);


}
