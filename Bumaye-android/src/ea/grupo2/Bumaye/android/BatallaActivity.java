package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.BatallaAPI;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;

public class BatallaActivity extends Activity {


	String url;
	String serverAddress;
	String serverPort;
	String nombre;
	String contra;
	BatallaVO batalla;
	private BatallaAPI batallaApi;
	private UsrPersonajeAPI api;
	PersonajeVO personaje, enemigo, personaje_batalla;
	String strAtaq,strArma;
	TextView nombre_personaje, ataque_personaje, defensa_personaje;
	TextView nombre_enemigo, enemigo_ataque, enemigo_defensa, actualizaciones_batalla;
	ImageView ataque_1, ataque_2, ataque_3, ataque_4, ataque_5, ataque_6;
	ImageView cascoimagen, guantesimagen, corazaimagen, armaimagen, piernasimagen, botasimagen;
	ListView lv;
	private ProgressBar progressBar, enemigo_progressBar_vida;
	int progressStatus = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_batalla);

		url = (String) getIntent().getExtras().get("url");

		batallaApi = new BatallaAPI();
		api = new UsrPersonajeAPI();
		batalla=(BatallaVO) getIntent().getExtras().get("batalla");
		if (batalla.getIdbatalla()==0)
		{
			Toast.makeText(getApplicationContext(), "Server not active",
					Toast.LENGTH_LONG).show();
			finish();
		}

		//preparar la url para los equipamientos
		AssetManager assetManager = getAssets();
		Properties config = new Properties();
		try {
			config.load(assetManager.open("config.properties"));
			serverAddress = config.getProperty("server.address");
			serverPort = config.getProperty("server.port");
		} catch (IOException e) {
			finish();
		}
		personaje = (PersonajeVO) getIntent().getExtras().get("personaje");
		if (personaje.getIduser()==0)
		{
			Toast.makeText(getApplicationContext(), "Server not active",
					Toast.LENGTH_LONG).show();
			finish();
		}
		getWindow().setBackgroundDrawableResource(R.drawable.fondomarron);

		nombre_enemigo= (TextView) findViewById(R.id.enemigo_nombre);
		enemigo_ataque = (TextView) findViewById(R.id.enemigo_ataque);
		enemigo_defensa = (TextView) findViewById(R.id.enemigo_defensa);
		enemigo_progressBar_vida = (ProgressBar) findViewById(R.id.enemigo_progressBar_vida);
		actualizaciones_batalla =(TextView) findViewById(R.id.actualizaciones_batalla);
		
		nombre_personaje = (TextView) findViewById(R.id.personaje_nombre);
		ataque_personaje = (TextView) findViewById(R.id.persojane_ataque);
		defensa_personaje = (TextView) findViewById(R.id.persojane_defensa);
		progressBar = (ProgressBar) findViewById(R.id.progressBar_vida);

		cascoimagen = (ImageView)findViewById(R.id.ataque_casco);
		armaimagen = (ImageView)findViewById(R.id.ataque_arma);
		guantesimagen = (ImageView)findViewById(R.id.ataque_guantes); 
		corazaimagen= (ImageView)findViewById(R.id.ataque_coraza); 
		piernasimagen = (ImageView)findViewById(R.id.ataque_perneras); 
		botasimagen = (ImageView)findViewById(R.id.ataque_botas); 

		lv=(ListView) findViewById(R.id.listObjetos_personaje);
		String [] nombre_Objetos = getnomObjetos(personaje);
		int [] cantidad_Objetos = getcantidadObjetos(personaje);
		lv.setAdapter(new CustomAdapterBatalla(this, nombre_Objetos, cantidad_Objetos));



		enemigo = batalla.getEnemigo(personaje.getNombre());
		refrescarAtributosEnemigo(enemigo);
		personaje_batalla = batalla.getEnemigo(enemigo.getNombre());
		refrescarAtributosPersonaje(batalla.getEnemigo(enemigo.getNombre()));
		cargarAtaques();
		
		actualizaciones_batalla.setText("Comienza la batalla");
		
		es_mi_turno();

	}

	public void refrescarAtributosEnemigo (PersonajeVO enemi)
	{
		nombre_enemigo.setText(enemi.getNombre());
		enemigo_ataque.setText("Ataque  "+ Float.toString(enemi.getAtaque()));
		enemigo_defensa.setText("Defensa  "+Float.toString(enemi.getDefensa()));
		enemigo_progressBar_vida.setProgress((int)enemi.getVida());

	}


	public void refrescarAtributosPersonaje (PersonajeVO per)
	{
		progressBar.setProgress((int)per.getVida());
		nombre_personaje.setText(per.getNombre());
		ataque_personaje.setText("Ataque  "+ Float.toString(per.getAtaque()));
		defensa_personaje.setText("Defensa  "+Float.toString(per.getDefensa()));


	}

	public String[] getnomObjetos (PersonajeVO person){

		int cantidad=cantidadObjetos();
		List<String> objetosrepetidos= new ArrayList<String>(cantidad);
		String [] nomObjetos = new String[cantidad];
		int i=0;
		for(ObjetoCantidadVO objet: person.getInventario())
		{
			if(objetosrepetidos.contains(objet.getNombre())==false)
			{
				Log.d("TIPO: ",objet.getTipo());
				if (objet.getTipo().equals("vida") || objet.getTipo().equals("ataque") || objet.getTipo().equals("defensa"))
				{
					objetosrepetidos.add(objet.getNombre());
					nomObjetos[i]= objet.getNombre();
					i++;
				}
			}
		}
		return nomObjetos;		
	}

	public int[] getcantidadObjetos (PersonajeVO person){

		int cantidad=cantidadObjetos();
		int [] canObjetos = new int[cantidad];
		List<String> objetosrepetidos= new ArrayList<String>(cantidad);
		int i=0;
		for(ObjetoCantidadVO objet: person.getInventario())
		{
			if(objetosrepetidos.contains(objet.getNombre())==false)
			{
				if (objet.getTipo().equals("vida") || objet.getTipo().equals("ataque") || objet.getTipo().equals("defensa"))
				{
					objetosrepetidos.add(objet.getNombre());
					canObjetos[i]= objet.getCantidad();
					i++;
				}
			}

		}
		return canObjetos;		
	}

	public int cantidadObjetos ()
	{
		int cantidad=0;
		List<String> objetosrepetidos= new ArrayList<String>(cantidad);
		for(ObjetoCantidadVO objet: personaje.getInventario())
		{
			if(objetosrepetidos.contains(objet.getNombre())==false)
			{
				if(objet.getTipo().equals("vida") || objet.getTipo().equals("ataque") || objet.getTipo().equals("defensa"))
				{
					objetosrepetidos.add(objet.getNombre());
				}
			}

		}
		cantidad=cantidad+objetosrepetidos.size();

		return cantidad;
	}

	public void cargarAtaques ()
	{
		//int contador_ataques=0;

		for (ArmaArmaduraVO arm: personaje.getArmasarmaduras()) {

			//solo la pinta si esta equipada
			if (arm.getEquipada()==1){

				for (AtaqueVO atac: arm.getAtaques()) {

					if(atac!=null)
					{
						Uri uri = Uri.parse("android.resource://ea.grupo2.Bumaye.android/drawable/"+arm.getNombre());

						if (arm.getTipo().equals("arma"))
						{
							armaimagen.setImageURI(uri);

						}else if(arm.getTipo().equals("casco"))
						{
							cascoimagen.setImageURI(uri);

						}else if(arm.getTipo().equals("guantes"))
						{
							guantesimagen.setImageURI(uri);

						}else if(arm.getTipo().equals("coraza"))
						{
							corazaimagen.setImageURI(uri);

						}else if(arm.getTipo().equals("perneras"))
						{
							piernasimagen.setImageURI(uri);

						}else if(arm.getTipo().equals("botas"))
						{
							//botas
							botasimagen.setImageURI(uri);
						}
					}
				}
			}

		}
	}

	public void es_mi_turno()
	{
		int mod = batalla.getTurno() % 2;
		int posicionBatalla=batalla.posicionJugador(personaje.getIduser());
		Log.i("MOD Y POSICION","mod: "+mod+" posicion: "+posicionBatalla);
		if (mod == posicionBatalla){
			
			
			actualizaciones_batalla.setText("Es tu turno !!BUMAYE¡¡");

		}
		else{
			//no es tu turno

			//modificar para que salga en el dilogo de batalla

			actualizaciones_batalla.setText("No es tu turno");

			try {

				Thread.sleep(2000);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

			url = "http://" + serverAddress + ":" + serverPort
					+ "/Bumaye-api/batalla/"+ batalla.getIdbatalla();
			Log.d("URL BATALLA",url);
			(new comprovacionPeticionTask()).execute(url);
		}
	}

	public void enBatalla_Click(View view)
	{
		//es tu turno??
		int mod = batalla.getTurno() % 2;
		int posicionBatalla=batalla.posicionJugador(personaje.getIduser());
		Log.i("MOD Y POSICION","mod: "+mod+" posicion: "+posicionBatalla);
		if (mod == posicionBatalla){
			//es tu turno


			if (view.getId()==R.id.ataque_casco)
			{
				for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
				{
					if (arm.getEquipada()==1){
						AtaqueVO ataces = new AtaqueVO();

						for (AtaqueVO atac: arm.getAtaques()) {

							ataces=atac;
						}

						if (arm.getTipo().equals("casco")){
							if(ataces.getNombreataque()==null){
								ataces.setNombreataque("sin ataque");
							}

							preparar_Url(ataces.getIdataque());
							///Funcion de mandar ataque a la api
						}
					}
				}
			}
			if (view.getId()==R.id.ataque_guantes)
			{
				for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
				{
					if (arm.getEquipada()==1){
						AtaqueVO ataces = new AtaqueVO();

						for (AtaqueVO atac: arm.getAtaques()) {

							ataces=atac;
						}

						if (arm.getTipo().equals("guantes")){
							if(ataces.getNombreataque()==null){
								ataces.setNombreataque("sin ataque");
							}
							///Funcion de mandar ataque a la api
							preparar_Url(ataces.getIdataque());
						}
					}
				}
			}
			if (view.getId()==R.id.ataque_coraza)
			{
				for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
				{
					if (arm.getEquipada()==1){
						AtaqueVO ataces = new AtaqueVO();

						for (AtaqueVO atac: arm.getAtaques()) {

							ataces=atac;
						}

						if (arm.getTipo().equals("coraza")){
							if(ataces.getNombreataque()==null){
								ataces.setNombreataque("sin ataque");
							}
							///Funcion de mandar ataque a la api
							preparar_Url(ataces.getIdataque());
						}
					}
				}
			}
			if (view.getId()==R.id.ataque_arma)
			{
				for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
				{
					if (arm.getEquipada()==1){
						AtaqueVO ataces = new AtaqueVO();

						for (AtaqueVO atac: arm.getAtaques()) {

							ataces=atac;
						}

						if (arm.getTipo().equals("arma")){
							if(ataces.getNombreataque()==null){
								ataces.setNombreataque("sin ataque");
							}
							///Funcion de mandar ataque a la api
							preparar_Url(ataces.getIdataque());
						}
					}
				}
			}
			if (view.getId()==R.id.ataque_perneras)
			{
				for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
				{
					if (arm.getEquipada()==1){
						AtaqueVO ataces = new AtaqueVO();

						for (AtaqueVO atac: arm.getAtaques()) {

							ataces=atac;
						}

						if (arm.getTipo().equals("perneras")){
							if(ataces.getNombreataque()==null){
								ataces.setNombreataque("sin ataque");
							}
							///Funcion de mandar ataque a la api
							preparar_Url(ataces.getIdataque());
						}
					}
				}
			}
			if (view.getId()==R.id.ataque_botas)
			{
				for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
				{
					if (arm.getEquipada()==1){
						AtaqueVO ataces = new AtaqueVO();

						for (AtaqueVO atac: arm.getAtaques()) {

							ataces=atac;
						}

						if (arm.getTipo().equals("botas")){
							if(ataces.getNombreataque()==null){
								ataces.setNombreataque("sin ataque");
							}

							//funcion de pusi y 
							preparar_Url(ataces.getIdataque());
						}
					}
				}
			}



		}
		else{

		}
	}

	public void preparar_Url(int idatacque)
	{
		url = "http://" + serverAddress + ":" + serverPort
				+ "/Bumaye-api/batalla/"+ batalla.getIdbatalla() +"/jugador/"+personaje.getIduser()+"/ataque/"+ idatacque;
		Log.d("URL BATALLA",url);
		(new AtaqueTask()).execute(url);
	}

	private class AtaqueTask extends AsyncTask<String, Void, BatallaVO> {
		@Override
		protected BatallaVO doInBackground(String... params) {
			BatallaVO enbatalla  = new BatallaVO();
			enbatalla = batallaApi.ataque_enBatalla(params[0]);			
			return enbatalla;
		}

		@Override
		protected void onPostExecute(BatallaVO result) {
			Log.d("Batalla actualizada id:",""+result.getIdbatalla());
			if (result!= null)
			{
				try {

					Thread.sleep(2000);

				} catch (InterruptedException e) {

					e.printStackTrace();

				}
				refrescar_batalla(result);
			}	
		}

		@Override
		protected void onPreExecute() {

		}
	}

	private void refrescar_batalla(BatallaVO batalla_actualizada){
		
		batalla = batalla_actualizada;
		enemigo = batalla_actualizada.getEnemigo(personaje.getNombre());
		refrescarAtributosEnemigo(enemigo);
		personaje_batalla=batalla_actualizada.getEnemigo(enemigo.getNombre());
		refrescarAtributosPersonaje(batalla_actualizada.getEnemigo(enemigo.getNombre()));
		
		if (enemigo.getVida()<=0)
		{
			
			actualizaciones_batalla.setText(" ¡VICTORIA!  has derrotado a tu oponente");
//			SharedPreferences prefs = getSharedPreferences("upc.eetac.ea.bumaye",Context.MODE_PRIVATE); 
//			nombre = prefs.getString("nombre", "");
//			contra = prefs.getString("password", "");	
//			(new LoginUsrTask()).execute(nombre, contra, url);
			try {

				Thread.sleep(6000);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}
			
			Intent intent = new Intent(this, PresentacionActivity.class);
			startActivity(intent);
			finish();
		
		}
		else if(personaje_batalla.getVida()<=0)
		{
			
//			
//			SharedPreferences prefs = getSharedPreferences("upc.eetac.ea.bumaye",Context.MODE_PRIVATE); 
//			nombre = prefs.getString("nombre", "");
//			contra = prefs.getString("password", "");	
//			(new LoginUsrTask()).execute(nombre, contra, url);
			actualizaciones_batalla.setText(" Derrota...  has sido deshonrado");
			try {

				Thread.sleep(6000);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}
			Intent intent = new Intent(this, PresentacionActivity.class);
			startActivity(intent);
			finish();
			
		}else
		{
		es_mi_turno();
		}
	}

	private class comprovacionPeticionTask extends AsyncTask<String, Void, BatallaVO> {

		@Override
		protected BatallaVO doInBackground(String... params) {
			BatallaVO batallavo  = new BatallaVO();
			Log.d("Enviando azeptacion","OOOOOOOOOOOOOOOuli shiet");
			batallavo=batallaApi.aceptacion_peticionBatalla(params[0]);			
			return batallavo;
		}

		@Override
		protected void onPostExecute(BatallaVO result) {
			if (result !=null)
			{
				Log.d("Obteniendo resultado: ","idBatalla: "+result.getIdbatalla());
				refrescar_batalla(result);
			}
		}

		@Override
		protected void onPreExecute() {

		}
	}
	
	private class LoginUsrTask extends AsyncTask<String, Void, PersonajeVO> {

		@Override
		protected PersonajeVO doInBackground(String... params) {
			PersonajeVO person = new PersonajeVO();
			person = api.loginUsr(params[0], params[1], params[2]);

			return person;
		}

		@Override
		protected void onPostExecute(PersonajeVO result) {
			if (result.getNombre() != "") {
				if (result.getIduser() == 0) {
					Toast.makeText(getApplicationContext(),
							"Server not active", Toast.LENGTH_LONG).show();
					finish();
				} else {
					Logeado(result);
				}
			} else {
			}
		}

		@Override
		protected void onPreExecute() {
			
		}
	}

	private void Logeado(PersonajeVO person) {
		
		Intent intent = new Intent(this, PerfilActivity.class);
		intent.putExtra("url", url);
		intent.putExtra("personaje", person);
		startActivity(intent);
		finish();
	}

}
