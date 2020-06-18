package com.sootbas.sootbasapp.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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
                if (item.getFeedUrl().contains(Constants.FEED_BURNER_BASE_URL)) { // FIXME
                    Utils.showSnackbar(mLayout, getString(R.string.feed_not_available));
                } else {
                    executeEpisodeQuery(item);
                }
            } else {
                Utils.showSnackbar(mLayout, getString(R.string.feed_not_available));
            }
        } else {
            Utils.showSnackbar(mLayout, getString(R.string.no_network_connection));
        }
    }
    // END






    public static void launch(Activity activity, ArrayList<Podcast> list, String title, boolean isSearch) {
        Intent intent = new Intent(activity, PodcastActivity.class);


        intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, list);

        intent.putExtra(Constants.PODCAST_TITLE, title);
        intent.putExtra(Constants.PODCAST_SEARCH, isSearch);

        activity.startActivity(intent);
    }

    public static void launch(Activity activity, Podcast item) {
        Intent intent = new Intent(activity, PodcastActivity.class);


         intent.putExtra(Constants.PODCAST_ITEM, item);

//        intent.putExtra(Constants.PODCAST_TITLE, "Self Learning");
//        intent.putExtra(Constants.PODCAST_SEARCH, false);
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
        mProgressBar.setVisibility(View.GONE);

        // instantiate the toolbar with up nav arrow and set page title
        initToolbar();

        boolean isSearch = getIntent().getBooleanExtra(Constants.PODCAST_SEARCH, false);
        String title = getIntent().getStringExtra(Constants.PODCAST_TITLE);
        if (title != null) {
            if (isSearch) {
                setTitle(String.format(Locale.ENGLISH, "Results for : %s", title));
            } else {
//                setTitle(String.format(Locale.ENGLISH, "Genre : %s", title));
            }
        }



        // retrieve the genreId and load the genre fragment
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            ArrayList<Podcast> list = getIntent().getParcelableArrayListExtra(Constants.PODCAST_LIST);
            initFragment(PodcastFragment.newInstance(list));
        }

        boolean isOpenDirectly = getIntent().getBooleanExtra(Constants.DIRECTLY_OPEN_TRENDING, false);
        Podcast podcast_open_directly = getIntent().getParcelableExtra(Constants.PODCAST_ITEM);

        if (isOpenDirectly == true)
        {
            if (podcast_open_directly != null) {
                executeEpisodeQuery(podcast_open_directly);
            }
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
                if(response.body()!= null) // very important incase the rss feed for anyreason was empty (or podcast was removed)
                {
                    Channel channel = response.body().getChannel();
                    if (channel != null && channel.getItemList() != null && channel.getItemList().size() > 0) {
                        // save feed to an in-memory cache since it's too large to send via IPC/intent
                        EpisodesDataCache.getInstance().setPodcast(item);
                        EpisodesDataCache.getInstance().setChannel(channel);
                        EpisodesActivity.launch(PodcastActivity.this);
                        finish();
                    } else {
                        Utils.showSnackbar(mLayout, getString(R.string.error_downloading_episode_list));
                    }
                }
                else {
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
