package com.hashmals.herophone;

import android.app.Fragment;
import android.app.WallpaperManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CallFragment extends Fragment {
    private AudioPlayer mPlayer = new AudioPlayer();
    private String mContact = CallReceiver.getName();
    private TextView mNumberText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlayer.play(getActivity());
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_call, parent, false);

        final WallpaperManager wallpaperManager  = WallpaperManager.getInstance(v.getContext());
        final Drawable wallpaper = wallpaperManager.getDrawable();

        if (Build.VERSION.SDK_INT >= 16) {
            v.setBackground(wallpaper);
        }
        else {
            v.setBackgroundDrawable(wallpaper);
        }

        mNumberText = (TextView)v.findViewById(R.id.text);
        if (mContact != null) {
            mNumberText.setText(mContact);
        } else {
            mNumberText.setText(CallReceiver.getNumber());
        }

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
