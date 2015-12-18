package com.bignerdranch.android.beatbox;

/**
 * Created by Daniel on 12/18/2015.
 * Class is created to keep track of sound Assets
 */
public class Sound {

    private String mAssetPath;
    private String mName;
    private Integer mSoundId;

    /**
     * Step 0 create a construtor that makes a presentable name for your sound
     * @param assetPath
     */
    public Sound(String assetPath) {
        mAssetPath = assetPath;
        //0.1 split off the filename
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        //0.2 strip the file extension
        mName = filename.replace(".wav", "");
    }

    /**
     * Generate necessary getters and setters
     */

    public String getAssetPath() {
        return mAssetPath;
    }
    public String getName() {
        return mName;
    }
    public Integer getSoundId() {
        return mSoundId;
    }
    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
