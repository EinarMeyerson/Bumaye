package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.Properties;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.BatallaAPI;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;

public class BatallaService extends Service {
	private final static String TAG = BatallaService.class.getName();
	private BatallaAPI batalla;
	private UsrPersonajeAPI api;
	String serverAddress;
	String serverPort;
	String iduser;
	PersonajeVO personaje = new PersonajeVO();
	String url;
	String url2;
	ProgressDialog pd;
	BatallaVO batall = new BatallaVO();
	private MediaPlayer media;
	private PowerManager.WakeLock lock;
	private Vibrator vibrator;
	private Thread vibrateThread;
	AlertDialog dialog;
	String nombre, contra;

	@Override
	public void onCreate() {
		batalla = new BatallaAPI();
		api = new UsrPersonajeAPI();
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
		nombre = prefs.getString("nombre", "");
		contra = prefs.getString("password", "");

		Log.e(TAG, "recibimos personaje: " + nombre);
		esperarVerificacion();
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
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
				+ "/Bumaye-api/batalla/comprovar/" + iduser;
		(new FetchInBackground()).execute(url);
	}

	private class FetchInBackground extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String aver = "No";
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.d("Comprobando batalla", "Enviando comprobacion");
			aver = batalla.comprovacion_peticionBatalla(params[0]);
			return aver;
		}

		@Override
		protected void onPostExecute(String result) {
			Log.e("Resultado comprovacion", result);
			if (result.equals(null)) {

			} else {
				if (result.equals("Si")) {
					alarmaLucha();
				} else {					
						esperarVerificacion();
				}
			}
		}

		private class AcceptInBackground extends
				AsyncTask<String, Void, BatallaVO> {

			@Override
			protected BatallaVO doInBackground(String... params) {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				url = "http://" + serverAddress + ":" + serverPort
						+ "/Bumaye-api/batalla/aceptar/" + iduser;
				BatallaVO batallavo = new BatallaVO();
				Log.d("Aceptando batalla", params[0]);

				batallavo = batalla.aceptacion_peticionBatalla(url);

				return batallavo;
			}

			@Override
			protected void onPostExecute(BatallaVO result) {

				batall = result;
				Log.d("Comprobando batall", "ID: " + result.getIdbatalla());
				iniciar_batalla(result);
				
			}

			@Override
			protected void onPreExecute() {

			}
		}

		private void alarmaLucha() {
			url2 = "http://" + serverAddress + ":" + serverPort
					+ "/Bumaye-api/user";
			(new LoginUsrTask()).execute(nombre, contra, url2);

			PowerManager power = (PowerManager) getSystemService(Context.POWER_SERVICE);
			lock = power.newWakeLock(PowerManager.FULL_WAKE_LOCK
					| PowerManager.ACQUIRE_CAUSES_WAKEUP,
					"AlarmReceiverActivity");
			vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			long[] pattern = { 0, 100, 1000};
			vibrator.vibrate(pattern, 0);
			
			lock.acquire();
			AlertDialog.Builder builder = new AlertDialog.Builder(
					getApplicationContext());
			builder.setTitle("A la batalla!")
					.setMessage("Te llaman a la batalla!").setCancelable(false)
					.setPositiveButton(R.string.aceptar, new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							vibrator.cancel();
							lock.release();
							(new AcceptInBackground()).execute(url);
						}// Ends onClick
					})
					.setNegativeButton(R.string.cancel, new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							vibrator.cancel();
							lock.release();							
							dialog.dismiss();
						}// Ends onClick
					});
			// Ends setButton
			dialog = builder.create();
			dialog.getWindow().setType(
					WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			dialog.show();
		}

		private void iniciar_batalla(BatallaVO batalla) {
			Intent intent = new Intent(getApplicationContext(),
					BatallaActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("url", url);
			intent.putExtra("batalla", batalla);
			intent.putExtra("personaje", personaje);
			startActivity(intent);
		}

		private class LoginUsrTask extends AsyncTask<String, Void, PersonajeVO> {
			@Override
			protected PersonajeVO doInBackground(String... params) {
				personaje = api.loginUsr(params[0], params[1], params[2]);
				return personaje;
			}

			@Override
			protected void onPostExecute(PersonajeVO result) {
				if (result == null) {
					Toast.makeText(getApplicationContext(),
							"Server not active", Toast.LENGTH_LONG).show();
				}
				// if (pd != null)
				// pd.dismiss();
			}

			@Override
			protected void onPreExecute() {
				// pd = new ProgressDialog(BatallaService.this);
				// pd.setTitle("Accediendo...");
				// pd.setCancelable(false);
				// pd.setIndeterminate(true);
				// pd.show();
			}
		}
	}

}