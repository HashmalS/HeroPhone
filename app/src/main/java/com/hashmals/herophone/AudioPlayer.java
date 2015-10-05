package com.hashmals.herophone;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {
	private MediaPlayer mPlayer;
	
	public void stop() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}

	
	public void play(Context c) {
		stop();

		mPlayer = MediaPlayer.create(c, R.raw.warning_sound);
		
		mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				stop();
			}
		});
		
		mPlayer.start();
		mPlayer.setLooping(true);
	}

}
