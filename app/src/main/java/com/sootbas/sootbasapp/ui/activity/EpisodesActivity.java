package com.sootbas.sootbasapp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.sootbas.sootbasapp.R;
import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.model.episode.Channel;
import com.sootbas.sootbasapp.model.episode.EpisodesDataCache;
import com.sootbas.sootbasapp.model.podcast.Podcast;
import com.sootbas.sootbasapp.ui.fragment.EpisodesFragment;

import static androidx.lifecycle.Lifecycle.State.RESUMED;
import static androidx.lifecycle.Lifecycle.State.STARTED;

public class EpisodesActivity extends BlankActivity implements
        EpisodesFragment.Contract{


    private static final long GAME_LENGTH_MILLISECONDS = 15000;
//    private static final String AD_UNIT_ID = "/6499/example/interstitial";
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"; // test ad id  https://developers.google.com/admob/android/interstitial?hl=en-GB
//    private static final String AD_UNIT_ID = "ca-app-pub-1445013205034544/6056124030"; // real ad id

    private static final String TAG = "Episodes Activity";

    private PublisherInterstitialAd interstitialAd;
    private CountDownTimer countDownTimer;
    private Button retryButton;
    private boolean gameIsInProgress;
    private boolean adIsLoading;
    private long timerMilliseconds;
    private static boolean isInForeground = false;

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
        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new PublisherInterstitialAd(this);
        // Defined in res/values/strings.xml
        interstitialAd.setAdUnitId(AD_UNIT_ID);

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startGame();
            }

            @Override
            public void onAdLoaded() {
                adIsLoading = false;
//                Toast.makeText(EpisodesActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                adIsLoading = false;
//                Toast.makeText(EpisodesActivity.this,
//                        "onAdFailedToLoad() with error code: " + errorCode,
//                        Toast.LENGTH_SHORT).show();
            }
        });



        startGame();

    }

    private void startGame() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!adIsLoading && !interstitialAd.isLoaded()) {
            adIsLoading = true;
            PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }

//        retryButton.setVisibility(View.INVISIBLE);
        resumeGame(GAME_LENGTH_MILLISECONDS);
    }

    private void resumeGame(long milliseconds) {
        // Create a new timer for the correct length and start it.
        gameIsInProgress = true;
        timerMilliseconds = milliseconds;
//if (!getLifecycle().getCurrentState().isAtLeast(RESUMED)){

    createTimer(milliseconds);
    countDownTimer.start();


    }

    private void createTimer(final long milliseconds) {
        // Create the game timer, which counts down to the end of the level
        // and shows the "retry" button.
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }



        countDownTimer = new CountDownTimer(milliseconds, 500) {
            @Override
            public void onTick(long millisUnitFinished) {
                timerMilliseconds = millisUnitFinished;
//                textView.setText("seconds remaining: " + ((millisUnitFinished / 1000) + 1));
            }

            @Override
            public void onFinish() {

//                textView.setText("done!");
//                retryButton.`setVisibility`(View.VISIBLE);
                if (isInForeground == false) {
                    gameIsInProgress = false;
                    showInterstitial();
                }
            }
        };
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
//            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            startGame();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isInForeground = true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        startGame();
        isInForeground = false;

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onAppBackgrounded() {
        //App in background

        Log.e(TAG, "************* backgrounded");
        Log.e(TAG, "************* ${isActivityVisible()}");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onAppForegrounded() {

        Log.e(TAG, "************* foregrounded");
        Log.e(TAG, "************* ${isActivityVisible()}");
        // App in foreground
    }
}
