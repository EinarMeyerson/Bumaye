package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;

public class RegisterActivity extends Activity {
	
	private final static String TAG = RegisterActivity.class.getName();
	String serverAddress;
	String serverPort;
	String nombre,mail;
	String contra, confirm;
	EditText username;
	EditText password;
	EditText passconf;
	EditText email;
	String url;
	private UsrPersonajeAPI api;

	public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = Integer.toString(R.string.app_id);
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    SharedPreferences prefs;
    Context context;

    String regid;
	
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

	}
	public void Regist(View v) {
		
		
		context = getApplicationContext();

        // Check device for Play Services APK. If check succeeds, proceed with
        //  GCM registration.
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);
		nombre = username.getText().toString();
		contra = password.getText().toString();
		confirm = passconf.getText().toString();
		mail = email.getText().toString();
		if (nombre.isEmpty() || contra.isEmpty() || mail.isEmpty())
		{
			Toast.makeText(getApplicationContext(), "Rellene todos los campos",
				   Toast.LENGTH_LONG).show();		
		}
		else{
			if (contra.equals(confirm))
				if (regid.isEmpty()) {
	                registerInBackground();
	            }
			else
				wrongConfirm();
		}	

		
	}
	public void Logear(View v){
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
			PersonajeVO person  = new PersonajeVO();
			person = api.registUsr(params[0],params[1],params[2],params[3],params[4]);
			
			return person;
		}

		@Override
		protected void onPostExecute(PersonajeVO result) {
			Log.d("LOGIN",result.toString());
			if (pd != null) {
				pd.dismiss();
			}
			if (result.getNombre() != "")
			{
				Log.e(TAG,result.getNombre());
				Registrado(result);
			}
			else
				userPillado();
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
	private void Registrado(PersonajeVO person){
		SharedPreferences prefs =
			     getSharedPreferences("upc.eetac.ea.bumaye",Context.MODE_PRIVATE);			 
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("nombre", nombre);
			editor.putString("password", contra);
			editor.commit();
		
		Intent intent = new Intent(this, PerfilActivity.class);
		intent.putExtra("url", url);
		intent.putExtra("personaje", person);
		startActivity(intent);
		finish();
	}
	private void wrongConfirm(){
		Toast.makeText(getApplicationContext(), "No coinciden las contraseñas",
				   Toast.LENGTH_LONG).show();
		password.setText("");
		passconf.setText("");
	}
	private void userPillado(){
		Toast.makeText(getApplicationContext(), "El usuario ya existe",
				   Toast.LENGTH_LONG).show();
		username.setText("");
	}
	
	
	/*       GCM CODIGO      !!!!!!!     */
	/**
	 * Gets the current registration ID for application on GCM service.
	 * <p>
	 * If result is empty, the app needs to register.
	 *
	 * @return registration ID, or empty string if there is no existing
	 *         registration ID.
	 */
	private String getRegistrationId(Context context) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	    if (registrationId.isEmpty()) {
	        Log.i(TAG, "Registration not found.");
	        return "";
	    }
	    // Check if app was updated; if so, it must clear the registration ID
	    // since the existing regID is not guaranteed to work with the new
	    // app version.
	    int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        Log.i(TAG, "App version changed.");
	        return "";
	    }
	    return registrationId;
	}
	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private SharedPreferences getGCMPreferences(Context context) {
	    // This sample app persists the registration ID in shared preferences, but
	    // how you store the regID in your app is up to you.
	    return getSharedPreferences(RegisterActivity.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}
	
	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	private static int getAppVersion(Context context) {
	    try {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionCode;
	    } catch (NameNotFoundException e) {
	        // should never happen
	        throw new RuntimeException("Could not get package name: " + e);
	    }
	}
	private void registerInBackground() {
	    new AsyncTask<Void, Void, String>() {
	        @Override
	        protected String doInBackground(Void... params) {
	            String msg = "";
	            try {
	                if (gcm == null) {
	                    gcm = GoogleCloudMessaging.getInstance(context);
	                }
	                regid = gcm.register(SENDER_ID);
	                msg = "Device registered, registration ID=" + regid;

	                // You should send the registration ID to your server over HTTP,
	                // so it can use GCM/HTTP or CCS to send messages to your app.
	                // The request to your server should be authenticated if your app
	                // is using accounts.
//	                sendRegistrationIdToBackend();

	                // For this demo: we don't need to send it because the device
	                // will send upstream messages to a server that echo back the
	                // message using the 'from' address in the message.

	                // Persist the regID - no need to register again.
	                storeRegistrationId(context, regid);
	            } catch (IOException ex) {
	                msg = "Error :" + ex.getMessage();
	                // If there is an error, don't just keep trying to register.
	                // Require the user to click a button again, or perform
	                // exponential back-off.
	            }
	            return msg;
	        }

	        @Override
	        protected void onPostExecute(String msg) {
	        	Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
				(new RegistUsrTask()).execute(nombre,contra,mail,regid,url);

	        }
	    }.execute(null, null, null);
	}
	private void storeRegistrationId(Context context, String regId) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    int appVersion = getAppVersion(context);
	    Log.i(TAG, "Saving regId on app version " + appVersion);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(PROPERTY_REG_ID, regId);
	    editor.putInt(PROPERTY_APP_VERSION, appVersion);
	    editor.commit();
	}
}
