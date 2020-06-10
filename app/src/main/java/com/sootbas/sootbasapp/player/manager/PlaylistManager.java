package com.sootbas.sootbasapp.player.manager;

import android.app.Application;
import android.app.Service;
import androidx.annotation.NonNull;

import com.devbrackets.android.playlistcore.manager.ListPlaylistManager;
import com.sootbas.sootbasapp.PodcastPlayerApplication;
import com.sootbas.sootbasapp.player.model.AudioItem;
import com.sootbas.sootbasapp.player.service.AudioService;


public class PlaylistManager extends ListPlaylistManager<AudioItem>{

    @NonNull
    @Override
    protected Application getApplication() {
        return PodcastPlayerApplication.getsApplication();
    }

    @NonNull
    @Override
    protected Class<? extends Service> getMediaServiceClass() {
        return AudioService.class;
    }

}
