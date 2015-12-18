package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
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

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();

    /**
     * Step 0 Create a constructor that takes Context, pulls out
     * an AssetManager then stashes the Context there
     */
    public BeatBox(Context context) {
        //0.1 stash context in AssetManager
        mAssets = context.getAssets();
        //0.2 look into your assets
        loadSounds();
    }

    /**
     * Step 1 Create a method to get a listing of the assets
     */
    private void loadSounds () {
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
        for(String filename : soundNames) {
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            mSounds.add(sound);
        }
    }

    /**
     * Step 2 create a getter method to retrieve list of sounds
     * @return list of sounds
     */
    public List<Sound> getSounds() {
        return mSounds;
    }
}
