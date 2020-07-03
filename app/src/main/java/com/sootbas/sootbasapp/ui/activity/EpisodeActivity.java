package com.sootbas.sootbasapp.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.sootbas.sootbasapp.R;
import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.common.Utils;
import com.sootbas.sootbasapp.ui.fragment.EpisodeFragment;

public class EpisodeActivity extends BlankActivity implements
        EpisodeFragment.Contract{

    // impl contract methods
    @Override
    public void onNavigationIconBackPressed() {
        onBackPressed();
    }

    @Override
    public void downloadEpisode() {
        Utils.showSnackbar(mLayout, "download");
    }

    @Override
    public void addEpisodeToPlaylist() {
        Utils.showSnackbar(mLayout, "add to playlist");
    }
    // END

    private FrameLayout mLayout;

    public static void launch(Activity activity, int position) {
        Intent intent = new Intent(activity, EpisodeActivity.class);
        intent.putExtra(Constants.EPISODE_SELECTED, position);
        activity.setTitle(R.string.app_name);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayout = (FrameLayout) findViewById(R.id.fragment_container);
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            int selected = getIntent().getIntExtra(Constants.EPISODE_SELECTED, 0);
            if (selected >= 0) {
                initFragment(EpisodeFragment.newInstance(selected));
            }
        }
    }

}
