package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.Properties;

import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private final static String TAG = LoginActivity.class.getName();
	String serverAddress;
	String serverPort;
	String nombre;
	String contra;
	EditText username;
	EditText password;
	String url;
	private UsrPersonajeAPI api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
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
					+ "/Bumaye-api/user";
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

	}
	public void Login(View v) {
		nombre = username.getText().toString();
		contra = password.getText().toString();
		if (nombre.isEmpty() || contra.isEmpty())
		{				
			Toast.makeText(getApplicationContext(), "Rellene todos los campos",
					   Toast.LENGTH_LONG).show();		
		}
		else{
			(new LoginUsrTask()).execute(nombre,contra,url);
		}		
	}
	
	// SE LLAMA AL METODO loginUsr DE LA API
	// SE LE MANDA LA URL Y DEVOLVEMOS UN PersonajeVO
	private class LoginUsrTask extends AsyncTask<String, Void, PersonajeVO> {
		private ProgressDialog pd;
		@Override
		protected PersonajeVO doInBackground(String... params) {
			PersonajeVO person  = new PersonajeVO();
			person = api.loginUsr(params[0],params[1],params[2]);			
			return person;
		}

		@Override
		protected void onPostExecute(PersonajeVO result) {
			Log.d("Login de:",result.getNombre().toString());
			if (pd != null) {
				pd.dismiss();
			}
			if (result.getNombre() != "")
			{
				Log.e(TAG,result.getNombre());
				Logeado(result);
			}
			else{				
				wronglogin();
			}		
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(LoginActivity.this);
			pd.setTitle("Logging...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}
	}
	
	private void Logeado(PersonajeVO person){
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
	private void wronglogin(){
		Toast.makeText(getApplicationContext(), "Incorrect user/pass",
				   Toast.LENGTH_LONG).show();
		password.setText("");
	}
}
