package com.sootbas.sootbasapp.player.service;


import android.support.annotation.IntRange;
import com.sootbas.sootbasapp.player.model.AudioItem;

import com.devbrackets.android.exomedia.listener.OnBufferUpdateListener;
import com.devbrackets.android.exomedia.listener.OnCompletionListener;
import com.devbrackets.android.exomedia.listener.OnErrorListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.listener.OnSeekCompletionListener;
import com.devbrackets.android.playlistcore.api.MediaPlayerApi;
//import com.devbrackets.android.playlistcore.listener.OnMediaBufferUpdateListener;
//import com.devbrackets.android.playlistcore.listener.OnMediaCompletionListener;
//import com.devbrackets.android.playlistcore.listener.OnMediaErrorListener;
//import com.devbrackets.android.playlistcore.listener.OnMediaPreparedListener;
//import com.devbrackets.android.playlistcore.listener.OnMediaSeekCompletionListener;
import com.devbrackets.android.playlistcore.listener.MediaStatusListener;

import org.jetbrains.annotations.NotNull;



public abstract class BaseMediaApi implements MediaPlayerApi<AudioItem>,
        OnPreparedListener,
        OnCompletionListener,
        OnErrorListener,
        OnSeekCompletionListener,
        OnBufferUpdateListener {

    protected boolean mPrepared;
    protected int mBufferPercent;

//    // define and register the appropriate listeners
//    protected OnMediaPreparedListener mMediaPreparedListener;
//    protected OnMediaCompletionListener mMediaCompletionListener;
//    protected OnMediaSeekCompletionListener mMediaSeekCompletionListener;
//    protected OnMediaErrorListener mMediaErrorListener;
//    protected OnMediaBufferUpdateListener  mBufferUpdateListener;

    protected MediaStatusListener<AudioItem> mediaStatusListener;


    @Override
    public void setMediaStatusListener(@NotNull MediaStatusListener<AudioItem> listener) {
        mediaStatusListener = listener;
    }


    //     forward exoplayer's listeners events
    @Override
    public void onPrepared() {
        mPrepared = true;
        if (mediaStatusListener != null) {
            mediaStatusListener.onPrepared(this);
        }
    }

    @Override
    public void onCompletion() {
        if (mediaStatusListener != null) {
            mediaStatusListener.onCompletion(this);
        }
    }

    @Override
    public boolean onError() {
        return mediaStatusListener != null && mediaStatusListener.onError(this);
    }

    @Override
    public void onSeekComplete() {
        if (mediaStatusListener != null) {
            mediaStatusListener.onSeekComplete(this);
        }
    }

    @Override
    public void onBufferingUpdate(@IntRange(from = 0L, to = 100L) int percent) {
        mBufferPercent = percent;
        if (mediaStatusListener != null) {
            mediaStatusListener.onBufferingUpdate(this, percent);
        }

    }

}
