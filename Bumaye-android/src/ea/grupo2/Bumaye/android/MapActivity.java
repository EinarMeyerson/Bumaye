package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.PopupWindow;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.CofreVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCofreCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.MapAPI;

public class MapActivity extends FragmentActivity {
	private final static String TAG = MapActivity.class.getName();
	private ListView navList;
	private DrawerLayout mDrawerLayout;
	private MapAPI api;
	String url;
	PopupWindow popUp;
	String serverAddress;
	String serverPort;
	private GoogleMap map;
	PersonajeVO personaje = null;
	ArrayList mSelectedItems = null;
	List<PersonajeVO> personajes = new ArrayList<PersonajeVO>();
	List<CofreVO> cofres = new ArrayList<CofreVO>();
	List<ObjetoCofreCantidadVO> objetos = new ArrayList<ObjetoCofreCantidadVO>();
	private ProgressDialog pdm;
	private ProgressDialog pdt;
	int i = 0;
	final float ZOOM=9.0f;
	Marker lastOpenned = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		personaje = (PersonajeVO) getIntent().getExtras().get("personaje");
		api = new MapAPI();
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
					.snippet("Recoger!")
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
		
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
		public boolean onMarkerClick(Marker marker) {
		    // Check if there is an open info window
		    if (lastOpenned != null) {
		        // Close the info window
		        lastOpenned.hideInfoWindow();

		        // Is the marker the same marker that was already open
		        if (lastOpenned.equals(marker)) {
		            // Nullify the lastOpenned object
		            lastOpenned = null;
		            // Return so that the info window isn't openned again
		            return true;
		        } 
		    }

		    // Open the info window for the marker
		    marker.showInfoWindow();
		    // Re-assign the last openned such that we can close it later
		    lastOpenned = marker;

		    // Event was handled by our code do not launch default behaviour.
		    return true;
		}
		});
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker arg0) {
				final String title = arg0.getTitle();
				String identif = arg0.getId();
				Dial(title, identif);
			}
		});

		map.setMyLocationEnabled(true);

		// EN ESTE PUNTO HAREMOS QUE EL MAPA TE SIGA SIEMPRE ASI SOLO
		// SE ATACAN A LOS QUE ESTAN CERCA
		map.setOnMyLocationChangeListener(myLocationChangeListener);

		// COMENTAR SI QUEREIS MOVER EL MAPA DENTRO DE LA APP!!!
		map.getUiSettings().setAllGesturesEnabled(false);
		map.getUiSettings().setMyLocationButtonEnabled(false);
		map.getUiSettings().setMapToolbarEnabled(false);;
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

	private void Dial2(List<ObjetoCofreCantidadVO> objeto) {
		ArrayList<String> items = new ArrayList<String>();
		for (int i = 0; i < objeto.size(); i++) {
			int o = (objeto.get(i).getIdobjeto());			
		}
		mSelectedItems = new ArrayList();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Cofre")
//		.setMultiChoiceItems(items, 0,
//                new DialogInterface.OnMultiChoiceClickListener() {
//         @Override
//         public void onClick(DialogInterface dialog, int which,
//                 boolean isChecked) {
//             if (isChecked) {
//                 // If the user checked the item, add it to the selected items
//                 mSelectedItems.add(which);
//             } else if (mSelectedItems.contains(which)) {
//                 // Else, if the item is already in the array, remove it 
//                 mSelectedItems.remove(Integer.valueOf(which));
//             }
//         }
//     })
// Set the action buttons
     .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int id) {
             // User clicked OK, so save the mSelectedItems results somewhere
             // or return them to the component that opened the dialog
        	 dialog.dismiss();
        	 
        	 // FUNCION RECOGER!!!
        	 
         }
     })
     .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int id) {
        	 dialog.dismiss();
         }
     }).show();

	}

	private void esperarLucha(String nom) {
		url = "http://" + serverAddress + ":" + serverPort
				+ "/Bumaye-api/user/idGCM/" + nom;
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
			
			if (i<6)
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
