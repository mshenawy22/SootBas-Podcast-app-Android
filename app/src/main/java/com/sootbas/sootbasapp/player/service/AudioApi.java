package com.sootbas.sootbasapp.player.service;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.os.PowerManager;

import com.devbrackets.android.exomedia.EMAudioPlayer;
//import com.devbrackets.android.playlistcore.api.AudioPlayerApi;
import com.devbrackets.android.playlistcore.manager.BasePlaylistManager;
import com.sootbas.sootbasapp.player.model.AudioItem;

import org.jetbrains.annotations.NotNull;


//public class AudioApi extends BaseMediaApi implements AudioPlayerApi{

    public class AudioApi extends BaseMediaApi {

        @NonNull
        private Context context;
        @NonNull
    private EMAudioPlayer mAudioPlayer;

//    public AudioApi(EMAudioPlayer player) {
public AudioApi(@NonNull Context context, @NonNull EMAudioPlayer player) {
        this.context = context.getApplicationContext();
        mAudioPlayer = player;
        mAudioPlayer.setOnPreparedListener(this);
        mAudioPlayer.setOnCompletionListener(this);
        mAudioPlayer.setOnErrorListener(this);
        mAudioPlayer.setOnBufferUpdateListener(this);
        mAudioPlayer.setOnSeekCompletionListener(this);
    }

    @Override
    public boolean isPlaying() {
        return mAudioPlayer.isPlaying();
    }

    @Override
    public void play() {
        mAudioPlayer.start();
    }

    @Override
    public void pause() {
        mAudioPlayer.pause();
    }

    @Override
    public void stop() {
        mAudioPlayer.stopPlayback();
    }

    @Override
    public void reset() {
        mAudioPlayer.reset();
    }

    @Override
    public void release() {
        mAudioPlayer.release();
    }

    @Override
    public void setVolume(@FloatRange(from = 0.0, to = 1.0) float left, @FloatRange(from = 0.0, to = 1.0) float right) {
        mAudioPlayer.setVolume(left, right);
    }

    @Override
    public void seekTo(@IntRange(from = 0L) long milliseconds) {
        mAudioPlayer.seekTo((int) milliseconds);
    }

    @Override
    public long getCurrentPosition() {
        return mAudioPlayer.getCurrentPosition();
    }

    @Override
    public long getDuration() {
        return mAudioPlayer.getDuration();
    }

    @Override
    public int getBufferedPercent() {
        return mAudioPlayer.getBufferPercentage();
    }

        @Override
        public boolean getHandlesOwnAudioFocus() {
            return false;
        }

        @Override
        public boolean handlesItem(@NotNull AudioItem item) {
            return false;
        }

        @Override
        public void playItem(@NotNull AudioItem item) {

        }
    }
