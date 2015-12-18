package com.bignerdranch.android.beatbox;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Main Activity where Fragment that carries RecyclerView will be hosted
 */
public class BeatBoxActivity extends SingleFragmentActivity {

    /**
     * Step 1 Create a Fragment that will be hosted by this activity
     * @return a new instance of this Fragment
     */
    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
