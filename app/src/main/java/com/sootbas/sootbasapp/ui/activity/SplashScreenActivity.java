package com.sootbas.sootbasapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sootbas.sootbasapp.R;
import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.common.PodcastsConstants;
import com.sootbas.sootbasapp.common.Utils;
import com.sootbas.sootbasapp.custom.NewsData;
import com.sootbas.sootbasapp.model.episode.Channel;
import com.sootbas.sootbasapp.model.episode.EpisodesDataCache;
import com.sootbas.sootbasapp.model.episode.Feed;
import com.sootbas.sootbasapp.model.genre.PodcastLists;
import com.sootbas.sootbasapp.model.podcast.Podcast;
import com.sootbas.sootbasapp.rest.RssClient;
import com.sootbas.sootbasapp.rest.RssInterface;
import com.sootbas.sootbasapp.ui.Adapters.MainNewsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class SplashScreenActivity extends AppCompatActivity{
    private Bundle args;
    String ChName ;
    String EpiNumber_S;
    int EpiNumber;
    private static final String TAG = "SplashActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


               if (IsNotificationPayloadValid() == true)
               {
                   switchToPodcast(ChName,EpiNumber);
               }
               else {

                   MainActivity.launch(SplashScreenActivity.this);
               }

                finish();

            }
        }, 2000);


    }


   boolean  IsNotificationPayloadValid()
    {
        Boolean CName_valid = false;
        Boolean ENumber_valid = false;
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                if (key.equals("CName"))
                {

                    ChName =(String) getIntent().getExtras().get(key);
                    CName_valid = true ;

                }
                else if (key.equals("ENumber")) {

                    EpiNumber_S = (String) getIntent().getExtras().get(key);
                    EpiNumber = Integer.parseInt(EpiNumber_S,10);
                    ENumber_valid = true ;

                }


            }


            if ( CName_valid && ENumber_valid == true ) {
           Log.d(TAG,  " Channel Name " + ChName);
                Log.d(TAG,  " Episode Number " + EpiNumber);
                Log.d(TAG,  "Done ");
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }


    public void switchToPodcast( String podcast_channel ,  int episode_number )

    {


        Podcast selected_podcast = PodcastsConstants.ElDa7ee7_podcast ;





        for(Podcast p : PodcastsConstants.All_podcasts)
        {

            if (podcast_channel.equals(p.getArtistName()))
            {

                selected_podcast = p;


            }

        }


        executeEpisodeQuery(selected_podcast , episode_number);



    }


    // download the podcast episode list
    private void executeEpisodeQuery(final Podcast item , int episodenumber ) {
        Timber.i("%s execute episode list download", Constants.LOG_TAG);

        if (episodenumber <1) episodenumber =1;
        else episodenumber -=1;
        final int episodeNumber = episodenumber;



        RssInterface rssService = RssClient.getClient().create(RssInterface.class); // takes no time
        Call<Feed> call = rssService.getItems(item.getFeedUrl());

//        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {

                Channel channel = response.body().getChannel();
//                progressBar.setVisibility(View.GONE);
                if (channel != null && channel.getItemList() != null && channel.getItemList().size() > 0) {
                    // save feed to an in-memory cache since it's too large to send via IPC/intent
                    EpisodesDataCache.getInstance().setPodcast(item);
                    EpisodesDataCache.getInstance().setChannel(channel);
//                    Intent player_intent = new Intent(getContext(), EpisodeActivity.class);
//                    player_intent.putExtra(Constants.EPISODE_SELECTED, episodeNumber);
//                    startActivity(player_intent);
                    EpisodeActivity.launch(SplashScreenActivity.this,episodeNumber);
                } else {
//                    Utils.showSnackbar(rootView, getString(R.string.error_downloading_episode_list));
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
                Timber.e("%s failure, error: %s", Constants.LOG_TAG, t.getMessage());
//                Utils.showSnackbar(rootView, getString(R.string.feed_not_available));
            }


        });
    }

    boolean CheckIfNetworkConnection () {

        /* *********** Checks if there is an internet connection ******/
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnected;

        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        isConnected = activeNetwork != null && activeNetwork.isConnected();


        return isConnected;

    }


}
