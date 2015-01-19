package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.Properties;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.WindowManager;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.BatallaAPI;


public class BatallaService extends Service {
	private final static String TAG = BatallaService.class.getName();
	private BatallaAPI batalla;
	String serverAddress;
	String serverPort;
	String iduser;
	String url;
	PersonajeVO personaje;
	private MediaPlayer media;
	private PowerManager.WakeLock lock;
	private Vibrator vibrator;
	private Thread vibrateThread;
	AlertDialog dialog;

	@Override
	public void onCreate() {
		batalla = new BatallaAPI();
		// COGEMOS IP Y PUERTO DEL SERVIDOR, DEFINIDOS EN LA CARPETA ASSETS
		AssetManager assetManager = getAssets();
		Properties config = new Properties();
		try {
			config.load(assetManager.open("config.properties"));
			serverAddress = config.getProperty("server.address");
			serverPort = config.getProperty("server.port");
			Log.d(TAG, "Configured server " + serverAddress + ":" + serverPort);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		SharedPreferences prefs = getSharedPreferences("upc.eetac.ea.bumaye",
				Context.MODE_PRIVATE);
		iduser = prefs.getString("iduser", "");

		esperarVerificacion();
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		personaje = (PersonajeVO) intent.getExtras().get("personaje");
		Log.e(TAG, "recibimos personaje: " + personaje.getNombre());
		esperarVerificacion();

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public class MyBinder extends Binder {
		public BatallaService getService() {
			return BatallaService.this;
		}
	}

	private void esperarVerificacion() {
		url = "http://" + serverAddress + ":" + serverPort
				+ "/Bumaye-api/batalla/aceptar/" + iduser;
		(new FetchInBackground()).execute(url);

	}

	private class FetchInBackground extends AsyncTask<String, Void, BatallaVO> {

		@Override
		protected BatallaVO doInBackground(String... params) {
			BatallaVO batallavo = new BatallaVO();
			batallavo.setIdbatalla(0);
			Log.d("Comprobando batalla", "OOOOOOOOOOOOOOOuli shiet");
			batallavo = batalla.comprovacion_peticionBatalla(params[0]);
			return batallavo;
		}

		@Override
		protected void onPostExecute(BatallaVO result) {
			if (batalla.equals(null)) {

			} else {
				if (result.getIdbatalla() != 0) {
					Log.d("Obteniendo resultado: ",
							"idBatalla: " + result.getIdbatalla());
					alarmaLucha(result);
				} else {
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					esperarVerificacion();
				}
			}
		}

		@Override
		protected void onPreExecute() {

		}
	}

	private void alarmaLucha(final BatallaVO batalla) {
		PowerManager power = (PowerManager) getSystemService(Context.POWER_SERVICE);
		lock = power.newWakeLock(PowerManager.FULL_WAKE_LOCK
				| PowerManager.ACQUIRE_CAUSES_WAKEUP, "AlarmReceiverActivity");
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vibrateThread = new VibrateThread();

		lock.acquire();
		AlertDialog.Builder builder = new AlertDialog.Builder(
				getApplicationContext());
		builder.setTitle("A la batalla!")
				.setMessage("El jugador "+batalla.getEnemigo(personaje.getNombre())+" te llama a la batalla")
				.setCancelable(false)
				.setPositiveButton(R.string.aceptar, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						media.stop();
						vibrateThread.interrupt();
						lock.release();
						iniciar_batalla(batalla);
					}// Ends onClick
				}).setNegativeButton(R.string.cancel, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						media.stop();
						vibrateThread.interrupt();
						lock.release();
						iniciar_batalla(batalla);
					}// Ends onClick
				});
		// Ends setButton
		dialog = builder.create();
		dialog.getWindow()
				.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.show();
		startAlarm(this);
		vibrateThread.start();
	}

	private void startAlarm(final Context context) {
		new Thread() {
			public void run() {
				Uri uri = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_ALARM);
				if (uri == null) {
					uri = RingtoneManager
							.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
					if (uri == null)
						uri = RingtoneManager
								.getDefaultUri(RingtoneManager.TYPE_ALARM);
				}// Ends if

				media = new MediaPlayer();
				try {
					media.setDataSource(context, uri);
					final AudioManager audioManager = (AudioManager) context
							.getSystemService(Context.AUDIO_SERVICE);
					if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
						media.setAudioStreamType(AudioManager.STREAM_ALARM);
						media.setLooping(true);
						media.prepare();
						media.start();
					}// Ends if
				}// Ends try
				catch (Exception e) {
				}
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
				long[] vibPattern = new long[] { 0L, 100L, 250L, 1000L, 250L,
						500L };
				vibrator.vibrate(vibPattern, 2);
			}// Ends try
			catch (Exception e) {
			}
		}// Ends run method
	}// Ends VibrateThread class

	private void iniciar_batalla(BatallaVO batalla) {
		Log.d("Abriendo la batalla", "Gasele");
		Intent intent = new Intent(this, BatallaActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("url", url);
		intent.putExtra("batalla", batalla);
		intent.putExtra("personaje", personaje);
		startActivity(intent);
	}

}