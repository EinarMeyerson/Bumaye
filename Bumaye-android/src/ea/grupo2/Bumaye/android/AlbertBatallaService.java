package ea.grupo2.Bumaye.android;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Vibrator;

public class AlbertBatallaService extends Service {
	private MediaPlayer media;
	private PowerManager.WakeLock lock;
	private AlertDialog alert;
	private Vibrator vibrator;
	private Thread vibrateThread;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	private void alarmaLucha() {
		 	PowerManager power = (PowerManager)getSystemService(Context.POWER_SERVICE);
	        lock = power.newWakeLock(PowerManager.FULL_WAKE_LOCK |
	            PowerManager.ACQUIRE_CAUSES_WAKEUP, "AlarmReceiverActivity");
	        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
	        vibrateThread = new VibrateThread();

	        lock.acquire();
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("A la batalla!")
			.setMessage("El jugador (poner) te llama a la batalla")
	        .setCancelable(false)
	        .setPositiveButton(R.string.aceptar, new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                media.stop();
	                vibrateThread.interrupt();
	                lock.release();
	            }// Ends onClick
	        })
	        .setNegativeButton(R.string.cancel, new OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                media.stop();
	                vibrateThread.interrupt();
	                lock.release();
	            }// Ends onClick
	        });
	        // Ends setButton

	        startAlarm(this);
	        vibrateThread.start();
	        alert.show();
	}
	private void startAlarm(final Context context) {
        new Thread() {
            public void run() {
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                if(uri == null) {
                    uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    if(uri == null)
                        uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                }// Ends if

                media = new MediaPlayer();
                try{
                    media.setDataSource(context, uri);
                    final AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
                        if(audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                            media.setAudioStreamType(AudioManager.STREAM_ALARM);
                            media.setLooping(true);
                            media.prepare();
                            media.start();
                        }// Ends if
                    }// Ends try
                    catch(Exception e){}
                }// Ends run method
            }.start();
        }// Ends startAlarm method

        class VibrateThread extends Thread {
            public VibrateThread() {
                super();
            }
            public void run() {               
                try {
                	// PATRON DE VIBRACION!
                    long[] vibPattern = new long[] {0L,100L,250L,1000L,250L,500L};
                    vibrator.vibrate(vibPattern, 2);
                }// Ends try
                catch (Exception e) {}
            }// Ends run method
        }// Ends VibrateThread class

}
