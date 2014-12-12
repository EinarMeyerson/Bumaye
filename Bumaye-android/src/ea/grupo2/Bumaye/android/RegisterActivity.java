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
				(new RegistUsrTask()).execute(nombre,contra,mail,url);
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
			person = api.registUsr(params[0],params[1],params[2],params[3]);
			
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
}
