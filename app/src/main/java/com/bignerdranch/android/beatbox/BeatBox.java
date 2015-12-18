package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 12/18/2015.
 * This class allows for the management of the assets
 */
public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    /**
     * Step 0 Create a constructor that takes Context, pulls out
     * an AssetManager then stashes the Context there
     */
    public BeatBox(Context context) {
        //0.1 stash context in AssetManager
        mAssets = context.getAssets();
        //0.3 Calling SoundPool constructor
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        //0.2 look into your assets
        loadSounds();
    }

    /**
     * Step 4 create a class that will play the sound
     * @param sound which to play sound
     */
    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        //4.1 have a check to make sure a file is there
        if (soundId == null)
            return;
        //4.2 call this method to play sound
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release() {
        mSoundPool.release();
    }

    /**
     * Step 1 Create a method to get a listing of the assets
     */
    private void loadSounds() {
        String[] soundNames;
        try {
            //1.1 assign list from asset to String array
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found" + soundNames.length + " sounds");
            //1.2 catch the possible error
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        //1.3 build a list of sounds from array of Strings
        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                //1.3.1 load all the songs into SoundPool
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }
    }

    /**
     * Step 2 create a getter method to retrieve list of sounds
     *
     * @return list of sounds
     */
    public List<Sound> getSounds() {
        return mSounds;
    }

    /**
     * Step 3 Load files into SoundPool for later playback
     *
     * @param sound ;specific sound to load
     */
    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }
}
