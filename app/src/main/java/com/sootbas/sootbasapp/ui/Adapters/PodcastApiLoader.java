package com.sootbas.sootbasapp.ui.Adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.sootbas.sootbasapp.custom.NewsData;
import com.sootbas.sootbasapp.custom.NewsLoader;

import java.util.ArrayList;
import java.util.List;

public class PodcastApiLoader  extends ArrayAdapter<NewsData> {
    private Context context;
    LoaderManager loaderManager;
    boolean isConnected;

    private MainNewsAdapter mAdapter;
//    Loader<List<NewsData>> StoriesList ;
    NewsLoader StoriesLoader;

    List<NewsData> StoriesData;
    public PodcastApiLoader(Context context, ArrayList<NewsData> pnewsData) {
        super(context, 0, pnewsData);

      this.context = context;

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        isConnected = activeNetwork != null &&
                activeNetwork.isConnected();

        Uri.Builder builder = Uri.parse("https://api.sootbas.com/trending.json").buildUpon();
        StoriesLoader = new NewsLoader(context, builder.toString());

        StoriesData= StoriesLoader.loadInBackground();

        isConnected = true;




    }

//    @NonNull
//    @Override
//    public Loader<List<NewsData>> onCreateLoader(int id, @Nullable Bundle args) {
//        Uri.Builder builder = Uri.parse("https://api.sootbas.com/trending.json").buildUpon();
//        return new NewsLoader(context, builder.toString());
//    }
//
//    @Override
//    public void onLoadFinished(@NonNull Loader<List<NewsData>> loader, List<NewsData> data) {
//
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull Loader<List<NewsData>> loader) {
//
//    }
}
