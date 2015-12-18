package com.bignerdranch.android.beatbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Daniel on 12/18/2015.
 * Abstract Fragment Activity is created to allow re-usability of code when
 * creating more fragments to use
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    /**
     * Step 1 Add the Fragment into the FragmentManager
     */
    @Override
    public void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_fragment);

        //1.1 Get Fragment Manager first so that can get fragment class
        FragmentManager fm = getSupportFragmentManager();
        //1.2 Ask fragment manager if fragment_container is in list
        Fragment fragment = fm.findFragmentById(R.id.activity_beat_box_fragment_container);
        //1.3 Checks if fragment manager found and returned fragment_container
        if(fragment == null){
            fragment = createFragment();
            //.beginTransaction() = creates and returns instance of FRAGMENT TRANSACTION
            //.add().commit() = create and commit FRAGMENT TRANSACTION
            fm.beginTransaction().add(R.id.activity_beat_box_fragment_container, fragment).commit();
        }
    }

}