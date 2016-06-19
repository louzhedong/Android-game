package com.example.sjc447245616.wulinshijie;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
	
	private MediaPlayer mediaPlayer;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public void onStart(Intent intent,int startId){
		super.onStart(intent, startId);
		
		if(mediaPlayer==null){
			mediaPlayer = MediaPlayer.create(this, R.raw.backmusic);
			mediaPlayer.setLooping(true);
			mediaPlayer.start();
		}
	}
	
	public void onDestroy(){
		super.onDestroy();
		mediaPlayer.stop();
	}
	
	
	
}
