package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.Properties;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;

public class RegisterActivity extends Activity {

	private final static String TAG = RegisterActivity.class.getName();
	String serverAddress;
	String serverPort;
	String nombre, mail;
	String contra, confirm;
	EditText username;
	EditText password;
	EditText passconf;
	EditText email;
	String url;
	private UsrPersonajeAPI api;
	String lat, lng;

//	public static final String EXTRA_MESSAGE = "message";
//	public static final String PROPERTY_REG_ID = "registration_id";
//	private static final String PROPERTY_APP_VERSION = "appVersion";
//	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
//	/**
//	 * Substitute you own sender ID here. This is the project number you got
//	 * from the API Console, as described in "Getting Started."
//	 */
//	String SENDER_ID = Integer.toString(R.string.app_id);
//	GoogleCloudMessaging gcm;
//	AtomicInteger msgId = new AtomicInteger();
	SharedPreferences prefs;
	Context context;
	public LocationManager locationManager;
	public MyLocationListener listener;
	//String regid;
	private ProgressDialog pdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		Log.d(TAG, "onCreate()");
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
			finish();
		}
		url = "http://" + serverAddress + ":" + serverPort
				+ "/Bumaye-api/user/register";
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		passconf = (EditText) findViewById(R.id.passconf);
		email = (EditText) findViewById(R.id.mail);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		listener = new MyLocationListener();

	}

	public void Regist(View v) {

		context = getApplicationContext();
		// Check device for Play Services APK. If check succeeds, proceed with
		// GCM registration.
		//gcm = GoogleCloudMessaging.getInstance(this);
		//regid = getRegistrationId(context);
		nombre = username.getText().toString();
		contra = password.getText().toString();
		confirm = passconf.getText().toString();
		mail = email.getText().toString();
		if (nombre.isEmpty() || contra.isEmpty() || mail.isEmpty()) {
			Toast.makeText(getApplicationContext(), "Rellene todos los campos",
					Toast.LENGTH_LONG).show();
		} else {
			if (contra.equals(confirm)) {
				locationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, 0, 0, listener);
				pdd = new ProgressDialog(RegisterActivity.this);
				pdd.setTitle("Getting Location...");
				pdd.setCancelable(false);
				pdd.setIndeterminate(true);
				pdd.show();

			} else
				wrongConfirm();
		}

	}

	public void Logear(View v) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	// SE LLAMA AL METODO registUsr DE LA API
	// SE LE MANDA LA URL Y DEVOLVEMOS UN PersonajeVO
	private class RegistUsrTask extends AsyncTask<String, Void, PersonajeVO> {
		private ProgressDialog pd;

		@Override
		protected PersonajeVO doInBackground(String... params) {
			PersonajeVO person = new PersonajeVO();
			person = api.registUsr(params[0], params[1], params[2], params[3],
					params[4], params[5], params[6]);

			return person;
		}

		@Override
		protected void onPostExecute(PersonajeVO result) {
			if (pd != null) {
				pd.dismiss();
			}
			if (result == null) {
				Toast.makeText(getApplicationContext(), "Server not active",
						Toast.LENGTH_LONG).show();
				finish();
			} else {
				if (result.getNombre() != "") {
					Registrado(result);
				} else
					userPillado();
			}
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(RegisterActivity.this);
			pd.setTitle("Registering...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}

	}

	private void Registrado(PersonajeVO person) {
		if (person == null) {
			Toast.makeText(getApplicationContext(), "Server not active",
					Toast.LENGTH_LONG).show();
			finish();
		} else {
			SharedPreferences prefs = getSharedPreferences(
					"upc.eetac.ea.bumaye", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("nombre", nombre);
			editor.putString("password", contra);
			editor.putString("iduser", Long.toString(person.getIduser()));
			editor.commit();
			Intent myIntent = new Intent(this, LocationService.class);
			startService(myIntent);
			Intent intent = new Intent(this, PerfilActivity.class);
			intent.putExtra("url", url);
			intent.putExtra("personaje", person);
			startActivity(intent);
			finish();
		}
	}

	private void wrongConfirm() {
		Toast.makeText(getApplicationContext(), "No coinciden las contraseñas",
				Toast.LENGTH_LONG).show();
		password.setText("");
		passconf.setText("");
	}

	private void userPillado() {
		Toast.makeText(getApplicationContext(), "El usuario ya existe",
				Toast.LENGTH_LONG).show();
		username.setText("");
	}

	public class MyLocationListener implements LocationListener {

		public void onLocationChanged(final Location loc) {
			lat = Double.toString(loc.getLatitude());
			lng = Double.toString(loc.getLongitude());
			if (lat.equals(null)) {
			} else {
				Log.e(TAG, "Lat: " + lat + " Long: " + lng);
				locationManager.removeUpdates(listener);
				if (pdd != null) {
					pdd.dismiss();
				}
				(new RegistUsrTask()).execute(nombre, contra, mail, null,
							lat, lng, url);
			}
		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

	}
}
