package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.Properties;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
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

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		personaje = (PersonajeVO) intent.getExtras().get("personaje");
		Log.e(TAG, "recibimos personaje: " + personaje.getNombre());

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void esperarVerificacion() {
		url = "http://" + serverAddress + ":" + serverPort
				+ "/Bumaye-api/batalla/aceptar/" + iduser;
		Log.d("URL BATALLA", url);
		(new FetchInBackground()).execute(url);

	}

	private class FetchInBackground extends AsyncTask<String, Void, BatallaVO> {

		@Override
		protected BatallaVO doInBackground(String... params) {
			BatallaVO batallavo = new BatallaVO();
			Log.d("Enviando azeptacion", "OOOOOOOOOOOOOOOuli shiet");
			batallavo = batalla.comprovacion_peticionBatalla(params[0]);
			return batallavo;
		}

		@Override
		protected void onPostExecute(BatallaVO result) {
			if (result != null) {
				Log.d("Obteniendo resultado: ",
						"idBatalla: " + result.getIdbatalla());
				iniciar_batalla(result);
			} else {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				esperarVerificacion();
			}
		}

		@Override
		protected void onPreExecute() {

		}
	}

	private void iniciar_batalla(BatallaVO batalla) {
		Log.d("Abriendo la batalla", "Gasele");
		Intent intent = new Intent(this, BatallaActivity.class);
		intent.putExtra("url", url);
		intent.putExtra("batalla", batalla);
		intent.putExtra("personaje", personaje);
		startActivity(intent);
	}

}