package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.CofreVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCofreCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.PeticionBatallaVO;
import ea.grupo2.Bumaye.android.api.BatallaAPI;
import ea.grupo2.Bumaye.android.api.MapAPI;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;

public class MapActivity extends FragmentActivity {
	private final static String TAG = MapActivity.class.getName();
	private ListView navList;
	private DrawerLayout mDrawerLayout;
	private MapAPI api;
	private UsrPersonajeAPI apip;
	String url,contra;
	NumberPicker np;
	private BatallaAPI batalla;
	PopupWindow popUp;
	String serverAddress;
	String serverPort;
	private GoogleMap map;
	PersonajeVO personaje = null;
	List<PersonajeVO> personajes = new ArrayList<PersonajeVO>();
	List<CofreVO> cofres = new ArrayList<CofreVO>();
	List<ObjetoCofreCantidadVO> objetos = new ArrayList<ObjetoCofreCantidadVO>();
	private ProgressDialog pdm;
	private ProgressDialog pdt;
	int i = 0;
	int cant = 1;
	final float ZOOM = 11.0f;
	Marker lastOpenned = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		personaje = (PersonajeVO) getIntent().getExtras().get("personaje");
		api = new MapAPI();
		apip = new UsrPersonajeAPI();
		batalla = new BatallaAPI();
		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		getWindow().setBackgroundDrawableResource(R.drawable.fondomarron);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));

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
		SharedPreferences prefs = getSharedPreferences("upc.eetac.ea.bumaye",Context.MODE_PRIVATE); 
		contra = prefs.getString("password", "");				
		
		url = "http://" + serverAddress + ":" + serverPort
				+ "/Bumaye-api/user/lista/" + personaje.getIduser();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Load an array of options names
		String[] names = getResources().getStringArray(R.array.nav_options);
		if (personaje.getNombre() != null)
			names[0] = Html.fromHtml("<b>" + personaje.getNombre() + "</b>")
			.toString();
		this.navList = (ListView) findViewById(R.id.left_drawer);
		// Set previous array as adapter of the list
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names);
		navList.setAdapter(adapter);
		navList.setOnItemClickListener(new DrawerItemClickListener());
		pdt = new ProgressDialog(MapActivity.this);
		pdt.setTitle("Cargando...");
		pdt.setCancelable(false);
		pdt.setIndeterminate(true);
		pdt.show();
		(new LoadListTask()).execute(url);
	}

	private class LoadListTask extends
	AsyncTask<String, Void, List<PersonajeVO>> {

		@Override
		protected List<PersonajeVO> doInBackground(String... params) {
			List<PersonajeVO> notas = api.getAllPerson(params[0]);
			return notas;
		}

		@Override
		protected void onPostExecute(List<PersonajeVO> result) {
			personajes = result;
			Log.e(TAG, "Lista de personajes tamaño: " + personajes.size());
			// CAMBIAR URL

			url = "http://" + serverAddress + ":" + serverPort
					+ "/Bumaye-api/user/listacofres";
			(new LoadList2Task()).execute(url);
		}

		@Override
		protected void onPreExecute() {

		}

	}

	private class LoadList2Task extends AsyncTask<String, Void, List<CofreVO>> {

		@Override
		protected List<CofreVO> doInBackground(String... params) {
			List<CofreVO> notas = api.getAllItem(params[0]);
			return notas;
		}

		@Override
		protected void onPostExecute(List<CofreVO> result) {
			cofres = result;
			Log.e(TAG, "Lista de cofres tamaño: " + cofres.size());
			opMapa();
		}

		@Override
		protected void onPreExecute() {

		}

	}

	private void opMapa() {
		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		//
		// MapFragment mapFragment = (MapFragment) getFragmentManager()
		// .findFragmentById(R.id.map);
		// mapFragment.getMapAsync(this);
		Log.e(TAG, "Dentro del mapa");
		map.clear();
		for (int i = 0; i < cofres.size(); i++) {
			CofreVO cof = cofres.get(i);
			map.addMarker(new MarkerOptions()
			.position(new LatLng(cof.getLatitud(), cof.getLongitud()))
					.title("Cofre")
					.snippet(Integer.toString(cof.getIdcofre()))
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.treasure_chest_marker)));
		}
		for (int i = 0; i < personajes.size(); i++) {
			PersonajeVO pers = personajes.get(i);
			map.addMarker(new MarkerOptions()
			.position(new LatLng(pers.getLatitud(), pers.getLongitud()))
			.snippet(
					"Ataque: " + pers.getAtaque() + ", Defensa: "
							+ pers.getDefensa() + ", Luchar?")
							.title(pers.getNombre())
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.contrario_marker)));

		}

		//map.setOnMarkerClickListener(new OnMarkerClickListener() {
			//public boolean onMarkerClick(Marker marker) {
				// Check if there is an open info window
				//if (lastOpenned != null) {
					// Close the info window
					//lastOpenned.hideInfoWindow();

					// Is the marker the same marker that was already open
					//if (lastOpenned.equals(marker)) {
						// Nullify the lastOpenned object
						//lastOpenned = null;
						// Return so that the info window isn't openned again
						//return true;
					//} 
				//}
		// CODIGO PARA QUE AL CLICAR AL MARKER NO SE MUEVA EL MAPA

				// Open the info window for the marker
				//marker.showInfoWindow();
				// Re-assign the last openned such that we can close it later
				//lastOpenned = marker;

				// Event was handled by our code do not launch default behaviour.
				//return true;
			//}
		//});
		// map.setOnMarkerClickListener(new OnMarkerClickListener() {
		// public boolean onMarkerClick(Marker marker) {
		// // Check if there is an open info window
		// if (lastOpenned != null) {
		// // Close the info window
		// lastOpenned.hideInfoWindow();
		//
		// // Is the marker the same marker that was already open
		// if (lastOpenned.equals(marker)) {
		// // Nullify the lastOpenned object
		// lastOpenned = null;
		// // Return so that the info window isn't openned again
		// return true;
		// }
		// }
		//
		// // Open the info window for the marker
		// marker.showInfoWindow();
		// // Re-assign the last openned such that we can close it later
		// lastOpenned = marker;
		//
		// // Event was handled by our code do not launch default
		// // behaviour.
		// return true;
		// }
		// });
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker arg0) {
				final String title = arg0.getTitle();
				String identif = arg0.getSnippet();
				Dial(title, identif);
			}
		});

		map.setMyLocationEnabled(true);
		map.setOnMyLocationChangeListener(myLocationChangeListener);

		// COMENTAR SI QUEREIS MOVER EL MAPA DENTRO DE LA APP!!!
		map.getUiSettings().setAllGesturesEnabled(false);
		map.getUiSettings().setMyLocationButtonEnabled(false);
		map.getUiSettings().setMapToolbarEnabled(false);
		;
	}

	private void Dial(final String title, String identif) {
		if (title.equals("Cofre")) {
			url = "http://" + serverAddress + ":" + serverPort
					+ "/Bumaye-api/user/listaobjetoscofre/" + identif;
			(new LoadCofreItemTask()).execute(url);

		} else {
			new AlertDialog.Builder(this)
					.setMessage("Quieres Luchar?")
					.setTitle(title)
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// User clicked OK button
									dialog.dismiss();
									pdm = new ProgressDialog(MapActivity.this);
									pdm.setTitle("Esperando oponente...");
									pdm.setCancelable(true);
									pdm.setIndeterminate(true);
									pdm.show();
									esperarLucha(title);
								}
							})
					.setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// User cancelled the dialog
									dialog.dismiss();
								}
							}).show();
		}
	}

	private void Dial2(final List<ObjetoCofreCantidadVO> objeto) {
		String[] items = new String[objeto.size()];
		for (int i = 0; i < objeto.size(); i++) {
			items[i] = objeto.get(i).getNombreObjeto() + ", cantidad: "
					+ objeto.get(i).getCantidad();
		}
		if (objeto.size() != 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Cofre")
					.setItems(items, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							AceptarCofre(objeto.get(which));
						}
					}).show();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Cofre")
					.setPositiveButton(R.string.aceptar,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
								}
							}).setMessage("Este cofre ya ha sido saqueado!")
					.show();
		}
	}

	private void AceptarCofre(final ObjetoCofreCantidadVO obj) {
		np = new NumberPicker(this);
		np.setMaxValue(obj.getCantidad());
		np.setMinValue(1);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		

		builder.setTitle("Recoger: " + obj.getNombreObjeto())
				// Set the action buttons
				.setView(np)
				.setPositiveButton(R.string.aceptar,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								cant = np.getValue();
								url = "http://" + serverAddress + ":"
										+ serverPort + "/Bumaye-api/user/"
										+ personaje.getIduser() + "/cofre/"
										+ obj.getIdcofre() + "/objeto/"
										+ obj.getIdobjeto() + "/cantidad/"
										+ np.getValue();
								(new GetObjectsTask()).execute(url);
								dialog.dismiss();
								Log.e(TAG, url);
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
							}
						}).show();
	}

	private void esperarLucha(String nom) {
		url = "http://" + serverAddress + ":" + serverPort
				+ "/Bumaye-api/batalla/peticion/"+personaje.getIduser()+"/" + nom;
		Log.d("Esperando Lucha","URL: "+url);
		(new esperarLuchaTask()).execute(url);
	}
	
	
	

	private class esperarLuchaTask extends AsyncTask<String, Void, String> {
		private ProgressDialog pd;
		@Override
		protected String doInBackground(String... params) {
			batalla.peticionBatalla(params[0]);			
			return "Peticion realizada";
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != "")
			{
				Log.d("Resultado peticion",result);
				esperarVerificacion();
			}
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(MapActivity.this);
			pd.setTitle("Esperando al contrincante");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}
	}
	private void esperarVerificacion()
	{
		url = "http://" + serverAddress + ":" + serverPort
				+ "/Bumaye-api/batalla/verificar/"+personaje.getIduser();
		Log.d("Esperando verificacionLucha","URL: "+url);
		(new esperarVerificacionTask()).execute(url);

	}

	private class esperarVerificacionTask extends AsyncTask<String, Void, PeticionBatallaVO> {
		private ProgressDialog pd;
		@Override
		protected PeticionBatallaVO doInBackground(String... params) {
			PeticionBatallaVO peticion = new PeticionBatallaVO();
			peticion=batalla.solicitudVerificaciona(params[0]);			
			return peticion;
		}

		@Override
		protected void onPostExecute(PeticionBatallaVO result) {
			if (result != null)
			{
				
				Log.i("Resultado peticion", "UUUUUUUUUUUUUUUli   "+result.getAceptada());
				if (result.getAceptada().equals("Si"))
				{
					url = "http://" + serverAddress + ":" + serverPort
							+ "/Bumaye-api/batalla/aceptarAtacante/"+personaje.getIduser();
					Log.d("URL BATALLA",url);
					(new comprovacionPeticionTask()).execute(url);
				}
				else
				{
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
			pd = new ProgressDialog(MapActivity.this);
			pd.setTitle("Esperando verificacion");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}
	}

	private class comprovacionPeticionTask extends AsyncTask<String, Void, BatallaVO> {

		@Override
		protected BatallaVO doInBackground(String... params) {
			BatallaVO batallavo  = new BatallaVO();
			Log.d("Enviando azeptacion","OOOOOOOOOOOOOOOuli shiet");
			batallavo=batalla.aceptacion_peticionBatalla(params[0]);			
			return batallavo;
		}

		@Override
		protected void onPostExecute(BatallaVO result) {
			if (result !=null)
			{
				Log.d("Obteniendo resultado: ","idBatalla: "+result.getIdbatalla());
				iniciar_batalla(result);
			}
		}

		@Override
		protected void onPreExecute() {

		}
	}

	private void iniciar_batalla(BatallaVO batalla){

		Log.d("Abriendo la batalla","Gasele");
		Intent intent = new Intent(this, BatallaActivity.class);
		intent.putExtra("url", url);
		intent.putExtra("personaje", personaje);
		intent.putExtra("batalla", batalla);
		startActivity(intent);
		finish();
	}

	private class LoadCofreItemTask extends
			AsyncTask<String, Void, List<ObjetoCofreCantidadVO>> {

		@Override
		protected List<ObjetoCofreCantidadVO> doInBackground(String... params) {
			List<ObjetoCofreCantidadVO> notas = api.getObjetos(params[0]);
			return notas;
		}

		@Override
		protected void onPostExecute(List<ObjetoCofreCantidadVO> result) {
			objetos = result;
			Log.e(TAG, "Lista de objetos tamaño: " + cofres.size());
			Dial2(objetos);
		}

		@Override
		protected void onPreExecute() {

		}

	}

	private class GetObjectsTask extends AsyncTask<String, Void, String> {
		private ProgressDialog pd;

		@Override
		protected String doInBackground(String... params) {
			String notas = api.acceptObjetos(params[0]);
			return notas;
		}

		@Override
		protected void onPostExecute(String result) {
			if (pd != null)
				pd.dismiss();
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG)
					.show();
			url = "http://" + serverAddress + ":" + serverPort + "/Bumaye-api/user";
			(new LoginUsrTask()).execute(personaje.getNombre(), contra, url);
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(MapActivity.this);
			pd.setTitle("Recogiendo...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}

	}
	// SE LLAMA AL METODO loginUsr DE LA API
		// SE LE MANDA LA URL Y DEVOLVEMOS UN PersonajeVO
		private class LoginUsrTask extends AsyncTask<String, Void, PersonajeVO> {

			@Override
			protected PersonajeVO doInBackground(String... params) {
				personaje = apip.loginUsr(params[0], params[1], params[2]);

				return personaje;
			}

			@Override
			protected void onPostExecute(PersonajeVO result) {				
					if (result.getIduser() == 0) {
						Toast.makeText(getApplicationContext(),
								"Server not active", Toast.LENGTH_LONG).show();
						finish();
					}
			}

			@Override
			protected void onPreExecute() {			
			}
		}
	
	
	

	private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
		@Override
		public void onMyLocationChange(Location location) {
			LatLng loc = new LatLng(location.getLatitude(),
					location.getLongitude());
			// Marker mMarker = map.addMarker(new
			// MarkerOptions().position(loc));
			if (pdt != null){
				pdt.dismiss();
			}
			if (map != null) {
				map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, ZOOM));
			}

			if (i<10)
			{
				i = i+1;				
			}
			else{
				i = 0;
				url = "http://" + serverAddress + ":" + serverPort
						+ "/Bumaye-api/user/lista/" + personaje.getIduser();
				(new LoadListTask()).execute(url);
			}
		}
	};

	private class DrawerItemClickListener implements
	ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			mDrawerLayout.closeDrawer(navList);
			navClic(position);
		}
	}

	private void navClic(int pos) {
		switch (pos) {
		case 0:
			Intent intent = new Intent(this, PerfilActivity.class);
			intent.putExtra("personaje", personaje);
			startActivity(intent);
			finish();
			break;
		case 1:
			// Intent intent = new Intent(this, MapActivity.class);
			// intent.putExtra("personaje", personaje);
			// startActivity(intent);
			// finish();
			break;
		case 2:
			Intent intenttt = new Intent(this, InventarioActivity.class);
			intenttt.putExtra("personaje", personaje);
			startActivity(intenttt);
			finish();
			break;
		default:
			break;
		}
	}
}
