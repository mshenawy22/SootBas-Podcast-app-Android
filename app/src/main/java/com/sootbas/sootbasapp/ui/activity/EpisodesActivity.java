package com.sootbas.sootbasapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sootbas.sootbasapp.R;
import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.model.episode.Channel;
import com.sootbas.sootbasapp.model.episode.EpisodesDataCache;
import com.sootbas.sootbasapp.model.podcast.Podcast;
import com.sootbas.sootbasapp.ui.fragment.EpisodesFragment;

public class EpisodesActivity extends BlankActivity implements
        EpisodesFragment.Contract{

    // impl of contract method
    @Override
    public void launchPlayer(int selected) {
        EpisodeActivity.launch(this, selected);
    }

    @Override
    public void addEpisodeToPlaylist() {
        // TODO
    }

    @Override
    public void downloadEpisode() {
        // TODO
    }

    @Override
    public void downloadError(String message) {
        // TODO send message back to PodcastActivity to display snackbar
        finish();
    }

    @Override
    public void onNavigationIconBackPressed() {
        onBackPressed();
    }
    // END

    public static void launch(Activity activity, Podcast item, Channel channel) {
        Intent intent = new Intent(activity, EpisodesActivity.class);
        intent.putExtra(Constants.PODCAST_ITEM, item);
        intent.putExtra(Constants.PODCAST_CHANNEL, channel);
        activity.startActivity(intent);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, EpisodesActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retrieve downloaded data from the cache
        Podcast item = EpisodesDataCache.getInstance().getPodcast();
        Channel channel = EpisodesDataCache.getInstance().getChannel();

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            if (item != null && channel != null)
                 initFragment(EpisodesFragment.newInstance(item, channel));
        }
    }


}
