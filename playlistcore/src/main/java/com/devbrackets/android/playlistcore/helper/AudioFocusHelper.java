/*
 * Copyright (C) 2016 Brian Wernick
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devbrackets.android.playlistcore.helper;

import android.content.Context;
import android.media.AudioManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A helper to simplify audio focus procedures in to simple callbacks and/or
 * EventBus events.
 */
public class AudioFocusHelper {
    /**
     * Basic AudioFocus callbacks.  These can also be accessed through
     * their corresponding EventBus events.
     */
    public interface AudioFocusCallback {
        /**
         * Occurs when the application gains audio focus
         *
         * @return True if the event has been handled
         */
        boolean onAudioFocusGained();

        /**
         * Occurs when the application looses audio focus
         *
         * @return True if the event has been handled
         */
        boolean onAudioFocusLost(boolean canDuckAudio);
    }

    public enum Focus {
        NONE,               // We haven't tried to obtain focus
        NO_FOCUS_NO_DUCK,   // Don't have focus, and can't duck
        NO_FOCUS_CAN_DUCK,  // don't have focus but can play at low volume ("Ducking")
        FOCUSED             // have full audio focus
    }

    @NonNull
    protected Focus currentFocus = Focus.NONE;
    @NonNull
    protected AudioManager audioManager;
    @Nullable
    protected AudioFocusCallback callbacks;
    @NonNull
    protected AudioFocusListener audioFocusListener = new AudioFocusListener();

    /**
     * Creates and sets up the basic information for the AudioFocusHelper.  In order to
     * be of any use you must call {@link #setAudioFocusCallback(AudioFocusCallback)}
     *
     * @param context The context for the AudioFocus (Generally Application)
     */
    public AudioFocusHelper(@NonNull Context context) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * Sets the AudioFocusCallback to inform of focus changes.
     *
     * @param callback The Callback to inform
     */
    public void setAudioFocusCallback(@Nullable AudioFocusCallback callback) {
        this.callbacks = callback;
    }

    /**
     * Retrieves the current audio focus
     *
     * @return The current Focus value currently held
     */
    @NonNull
    public Focus getCurrentAudioFocus() {
        return currentFocus;
    }

    /**
     * Requests to obtain the audio focus
     *
     * @return True if the focus was granted
     */
    public boolean requestFocus() {
        if (currentFocus == Focus.FOCUSED) {
            return true;
        }

        int status = audioManager.requestAudioFocus(audioFocusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED == status;
    }

    /**
     * Requests the system to drop the audio focus
     *
     * @return True if the focus was lost
     */
    public boolean abandonFocus() {
        if (currentFocus == Focus.NONE) {
            return true;
        }

        int status = audioManager.abandonAudioFocus(audioFocusListener);
        if (AudioManager.AUDIOFOCUS_REQUEST_GRANTED == status) {
            currentFocus = Focus.NONE;
        }

        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED == status;
    }

    /**
     * An internal listener that pays attention to the events from the
     * Android system, converting the values to {@link Focus} for easier
     * digestion.
     */
    protected class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    currentFocus = Focus.FOCUSED;
                    postAudioFocusGained();
                    break;

                case AudioManager.AUDIOFOCUS_LOSS:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    currentFocus = Focus.NO_FOCUS_NO_DUCK;
                    postAudioFocusLost(false);
                    break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    currentFocus = Focus.NO_FOCUS_CAN_DUCK;
                    postAudioFocusLost(true);
                    break;

                default:
                    break;
            }
        }

        public void postAudioFocusGained() {
            if (callbacks != null) {
                callbacks.onAudioFocusGained();
            }
        }

        public void postAudioFocusLost(boolean canDuck) {
            if (callbacks != null) {
                callbacks.onAudioFocusLost(canDuck);
            }
        }
    }
}
