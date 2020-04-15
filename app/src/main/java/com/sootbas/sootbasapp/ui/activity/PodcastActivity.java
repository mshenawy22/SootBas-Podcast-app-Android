package com.sootbas.sootbasapp.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.ProgressBar;

import com.sootbas.sootbasapp.R;
import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.common.Utils;
import com.sootbas.sootbasapp.model.episode.Channel;
import com.sootbas.sootbasapp.model.episode.EpisodesDataCache;
import com.sootbas.sootbasapp.model.episode.Feed;
import com.sootbas.sootbasapp.model.podcast.Podcast;
import com.sootbas.sootbasapp.rest.RssClient;
import com.sootbas.sootbasapp.rest.RssInterface;
import com.sootbas.sootbasapp.ui.fragment.PodcastFragment;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class PodcastActivity extends BaseActivity implements
        PodcastFragment.Contract{

    // impl of contract methods
    @Override
    public void onItemClick(Podcast item) {
        // launch EpisodesActivity and display Podcast Info and episode list
        if (Utils.isClientConnected(this)) {
            if (item.getFeedUrl() != null && !item.getFeedUrl().isEmpty()) {
//                if (item.getFeedUrl().contains(Constants.FEED_BURNER_BASE_URL)) { // FIXME
//                    Utils.showSnackbar(mLayout, getString(R.string.feed_not_available));
//                } else {
                    executeEpisodeQuery(item);
                //}
            } else {
                Utils.showSnackbar(mLayout, getString(R.string.feed_not_available));
            }
        } else {
            Utils.showSnackbar(mLayout, getString(R.string.no_network_connection));
        }
    }
    // END


  static  Podcast AhmedHassanwZeinab_podcast = new Podcast(

            /* artistName;*/"Ahmed jj Hassan Family",
            /* collectionName;*/ "AHmed jj Hassan Family vlogs",
            /* feedUrl; */ "https://anchor.fm/s/19653114/podcast/rss",
            /* artworkUrl600;*/"https://newmido22.000webhostapp.com/arab%20podcasts%20pics/ahmed%20w%20zenab%20600.jpg",
          "https://anchor.fm/ahmed-hassan-w-zeinab"

    );

    static  Podcast ObamaElMasry_podcast = new Podcast(

            /* artistName;*/"Obama El Masry",
            /* collectionName;*/ "Obama El Masry comedy",
            /* feedUrl; */ "https://anchor.fm/s/19661084/podcast/rss",
            /* artworkUrl600;*/"https://newmido22.000webhostapp.com/arab%20podcasts%20pics/obama%20el%20masry%20600.jpg",
            "https://anchor.fm/obama-el-masry"

    );

    static  Podcast NourhanKandil_podcast = new Podcast(

            /* artistName;*/"Dr Nourhan Kandil",
            /* collectionName;*/ "Dr Nourhan Kandil health tips",
            /* feedUrl; */ "https://anchor.fm/s/1958091c/podcast/rss",
            /* artworkUrl600;*/"https://newmido22.000webhostapp.com/arab%20podcasts%20pics/nourhan%20kandil%20600.jpg",

            "https://anchor.fm/nurhan-kandil"

    );






    public static void launch(Activity activity, ArrayList<Podcast> list, String title, boolean isSearch) {
        Intent intent = new Intent(activity, PodcastActivity.class);
        ArrayList <Podcast>  LifeStyle_Podcast_list = new ArrayList<Podcast>() ;
        ArrayList <Podcast>  Comedy_Podcast_list = new ArrayList<Podcast>() ;
        ArrayList <Podcast>  Health_Podcast_list = new ArrayList<Podcast>() ;
//        LifeStyle_Podcast_list.add(AhmedHassanwZeinab_podcast);
//        Comedy_Podcast_list.add(ObamaElMasry_podcast);
//        Health_Podcast_list.add(NourhanKandil_podcast);
//
//
//
//
//
//
//
//if (title == "Comedy") {
//    intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, Comedy_Podcast_list);
//}
//else if (title == "Kids_and_Family")
//{
//    intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, LifeStyle_Podcast_list);
//}
//else
//{
//    intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, Health_Podcast_list);
//}
        intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, list);

        intent.putExtra(Constants.PODCAST_TITLE, title);
        intent.putExtra(Constants.PODCAST_SEARCH, isSearch);
        activity.startActivity(intent);
    }

    private CoordinatorLayout mLayout;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);
        mLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setY(72f); // 66f

        // instantiate the toolbar with up nav arrow and set page title
        initToolbar();

        boolean isSearch = getIntent().getBooleanExtra(Constants.PODCAST_SEARCH, false);
        String title = getIntent().getStringExtra(Constants.PODCAST_TITLE);
        if (title != null) {
            if (isSearch) {
                setTitle(String.format(Locale.ENGLISH, "Results for : %s", title));
            } else {
                setTitle(String.format(Locale.ENGLISH, "Genre : %s", title));
            }
        }

        // retrieve the genreId and load the genre fragment
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            ArrayList<Podcast> list = getIntent().getParcelableArrayListExtra(Constants.PODCAST_LIST);
            initFragment(PodcastFragment.newInstance(list));
        }

    }


    // download the podcast episode list
    private void executeEpisodeQuery(final Podcast item) {
        Timber.i("%s execute episode list download", Constants.LOG_TAG);
        mProgressBar.setVisibility(View.VISIBLE);
        RssInterface rssService = RssClient.getClient().create(RssInterface.class);
        Call<Feed> call = rssService.getItems(item.getFeedUrl());
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                mProgressBar.setVisibility(View.GONE);
                Channel channel = response.body().getChannel();
                if (channel != null && channel.getItemList() != null && channel.getItemList().size() > 0) {
                    // save feed to an in-memory cache since it's too large to send via IPC/intent
                    EpisodesDataCache.getInstance().setPodcast(item);
                    EpisodesDataCache.getInstance().setChannel(channel);
                    EpisodesActivity.launch(PodcastActivity.this);
                } else {
                    Utils.showSnackbar(mLayout, getString(R.string.error_downloading_episode_list));
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Timber.e("%s failure, error: %s", Constants.LOG_TAG, t.getMessage());
                Utils.showSnackbar(mLayout, getString(R.string.feed_not_available));
            }

        });
    }

}
