package com.bignerdranch.android.beatbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by Daniel on 12/18/2015.
 * Create the fragment that will hold the RecyclerView containing all the buttons
 */
public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;

    /**
     * Step 0 create a method that creates new Instances of the Fragment
     * @return a new Instance of BeatBoxFragment
     */
    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);

        mBeatBox = new BeatBox(getActivity());
    }

    /**
     * Step 1 create onCreateView of the Fragment
     * @return the view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstanceState) {
        //1.1 inflate the View
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        //1.2 Wire up recyclerView
        RecyclerView rView = (RecyclerView) view.findViewById(R.id.fragment_beat_box_recycler_view);
        //1.3 setLayoutManager
        rView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //1.4 Wire up sound Adapter
        rView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return view;
    }

    /**
     * Step 2 Create the ViewHolder Class for the items in each recyclerView
     */
    private class SoundHelper extends RecyclerView.ViewHolder {
        //2.1 initialize the objects contained in View
        private Button mButton;
        private Sound mSound;
        //2.2 Wire up all the objects from the layout in the constructor class
        public SoundHelper(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_sound, container, false));

            mButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
        }
        //2.3  create a method to bind sound
        public void bindSound(Sound sound) {
            mSound = sound;
            mButton.setText(mSound.getName());
        }
    }

    /**
     * Step 3 Create the Adapter Class to bind the ViewHolders to RacyclerViews
     */
    private class SoundAdapter extends RecyclerView.Adapter<SoundHelper> {
        private List<Sound> mSounds;

        //3.5 create a constructor to bring the sounds list
        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @Override
        public SoundHelper onCreateViewHolder(ViewGroup container, int viewType) {
            //3.1 initialize inflater from context of Activity
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            //3.2 call ViewHolder with initialized inflater and pass container
            return new SoundHelper(inflater, container);
        }

        @Override
        public void onBindViewHolder(SoundHelper soundHelper, int position) {
            //3.3 Get position and then bind to holder
            Sound sound = mSounds.get(position);
            soundHelper.bindSound(sound);
        }

        //3.4 get the list size
        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
