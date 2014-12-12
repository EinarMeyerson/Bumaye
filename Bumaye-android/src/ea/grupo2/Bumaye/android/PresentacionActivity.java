package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class PresentacionActivity extends Activity{
	private final static String TAG = PresentacionActivity.class.getName();
	String serverAddress;
	String serverPort;
	String nombre;
	String contra;
	String url;
	private UsrPersonajeAPI api;
	Intent intentt;
    private static final long SPLASH_SCREEN_DELAY = 2000;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_presentacion);
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
				TimerTask task = new TimerTask() {
				      @Override
				      public void run() {
						   comprovacionLogin();				
				        }
				    };

				    Timer timer = new Timer();
				    timer.schedule(task, SPLASH_SCREEN_DELAY);
			}
			
		private void comprovacionLogin(){
			//Sirve para aceder al archivo de preferencias y recuperar el nombre y la contraseña
			SharedPreferences prefs = getSharedPreferences("upc.eetac.ea.bumaye",Context.MODE_PRIVATE); 
			nombre = prefs.getString("nombre", "");
			contra = prefs.getString("password", "");				
			Log.d("Nombre guardado: ",nombre);
	    								
			if (nombre!="")					
				(new LoginUsrTask()).execute(nombre,contra,url);
			else{
				intentt = new Intent(this, RegisterActivity.class);
				startActivity(intentt);	
				finish();
			}
		}
			// SE LLAMA AL METODO loginUsr DE LA API
			// SE LE MANDA LA URL Y DEVOLVEMOS UN PersonajeVO
			private class LoginUsrTask extends AsyncTask<String, Void, PersonajeVO> {
				@Override
				protected PersonajeVO doInBackground(String... params) {
					PersonajeVO person  = new PersonajeVO();
					person = api.loginUsr(params[0],params[1],params[2]);					
					return person;
				}
				@Override
				protected void onPostExecute(PersonajeVO result) {
					Log.d("Login",result.toString());
					if (result.getNombre() != "")
					{
						Logeado(result);
					}
					else{				
						wronglogin();
					}				
				}
				@Override
				protected void onPreExecute() {					
				}
			}
			private void Logeado(PersonajeVO person){
				Intent intent = new Intent(this, PerfilActivity.class);
				intent.putExtra("url", url);
				Log.d("PERSONAJE","Armaduras " + person.getArmasarmaduras());
				intent.putExtra("personaje", person);
				startActivity(intent);
				finish();
			}
			private void wronglogin(){
				Toast.makeText(getApplicationContext(), "Incorrect user/pass",
						   Toast.LENGTH_LONG).show();
			}		
	}
