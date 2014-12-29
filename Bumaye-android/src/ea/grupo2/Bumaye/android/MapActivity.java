package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ea.grupo2.Bumaye.ClasesVO.CofreVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.MapAPI;

public class MapActivity extends FragmentActivity{
	private final static String TAG = MapActivity.class.getName();
	private ListView navList;
    private DrawerLayout mDrawerLayout;
	private MapAPI api;
	String url;
	PopupWindow popUp;
	String serverAddress;
	String serverPort;
    private GoogleMap map;
	PersonajeVO personaje =null;
	List<PersonajeVO> personajes = new ArrayList<PersonajeVO>();
	List<CofreVO> cofres = new ArrayList<CofreVO>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);		
		personaje = (PersonajeVO) getIntent().getExtras().get("personaje");
		api = new MapAPI();
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

		 getWindow().setBackgroundDrawableResource(R.drawable.fondomarron);
	     getActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
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
				+ "/Bumaye-api/user/lista/"+personaje.getIduser();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Load an array of options names       
		String[] names = getResources().getStringArray(
				R.array.nav_options);
		if (personaje.getNombre()!=null)
			names[0] = Html.fromHtml("<b>"+personaje.getNombre()+"</b>").toString();
		this.navList = (ListView) findViewById(R.id.left_drawer);
		// Set previous array as adapter of the list
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names);
		navList.setAdapter(adapter);
		navList.setOnItemClickListener(new DrawerItemClickListener());
    	(new LoadListTask()).execute(url);		
    }  
	private class LoadListTask extends AsyncTask<String, Void, List<PersonajeVO>> {
		private ProgressDialog pd;

		@Override
		protected List<PersonajeVO> doInBackground(String... params) {
			List<PersonajeVO> notas = api.getAllPerson(params[0]);
			return notas;
		}

		@Override
		protected void onPostExecute(List<PersonajeVO> result) {						
			personajes = result;
	    	Log.e(TAG, "Lista de personajes tamaño: " +personajes.size());
	    	Log.e(TAG, "RECORDAR CAMBIAR URL PARA LISTA COFRES!!");
	    	// CAMBIAR URL
	    	
	    	url = "http://" + serverAddress + ":" + serverPort
					+ "/Bumaye-api/user/listacofres";
	    	(new LoadList2Task()).execute(url);		
			if (pd != null) {
				pd.dismiss();
			}
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(MapActivity.this);
			pd.setTitle("Loading Players...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}

	}

	private class LoadList2Task extends AsyncTask<String, Void, List<CofreVO>> {
		private ProgressDialog pd;

		@Override
		protected List<CofreVO> doInBackground(String... params) {
			List<CofreVO> notas = api.getAllItem(params[0]);
			return notas;
		}

		@Override
		protected void onPostExecute(List<CofreVO> result) {						
			cofres = result;
	    	Log.e(TAG, "Lista de cofres tamaño: " +cofres.size());
	    	opMapa();
			if (pd != null) {
				pd.dismiss();
			}
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(MapActivity.this);
			pd.setTitle("Loading Items...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}

	}
    private void opMapa(){
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
//        
//    	MapFragment mapFragment = (MapFragment) getFragmentManager()
//			    .findFragmentById(R.id.map);
//			mapFragment.getMapAsync(this);
        Log.e(TAG, "Dentro del mapa");
    	double Lon = 1.705791;
    	double Lat = 41.221788;
    	for (int i=0;  i < personajes.size() ; i++){
	    	PersonajeVO pers = personajes.get(i);
	    	Lon = Lon+1;
	    	Lat = Lat+1;
    		map.addMarker(new MarkerOptions()
            .position(new LatLng(Lat, Lon))
            .snippet("Luchar!")
            .title(pers.getNombre()+" - \nAtaque: "+pers.getAtaque()+"\nDefensa: "+pers.getDefensa()));
    		
		}    
    	for (int i=0;  i < cofres.size() ; i++){
	    	CofreVO cof = cofres.get(i);	    	
	    	Lon = cof.getLongitud();
	    	Lat = cof.getLatitud();
    		map.addMarker(new MarkerOptions()
            .position(new LatLng(Lat, Lon))
            .title("Cofre nº: "+cof.getIdcofre()));
    		
		}    
    	map.setOnInfoWindowClickListener(new OnInfoWindowClickListener()
    	{
    	    @Override public void onInfoWindowClick(Marker arg0) {
    	        final String title = arg0.getTitle();
    	        Toast.makeText(MapActivity.this, "A luchar contra: "+title,
    	                Toast.LENGTH_LONG).show();
    	    }
    	}); 
    	
    	 map.setMyLocationEnabled(true);
    	   
    	    // EN ESTE PUNTO HAREMOS QUE EL MAPA TE SIGA SIEMPRE ASI SOLO 
    	    // SE ATACAN A LOS QUE ESTAN CERCA
    	    map.setOnMyLocationChangeListener(myLocationChangeListener);
    	
    	    // COMENTAR SI QUEREIS MOVER EL MAPA DENTRO DE LA APP!!!
    	    map.getUiSettings().setAllGesturesEnabled(false);
    	    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
           // Marker mMarker = map.addMarker(new MarkerOptions().position(loc));
            if(map != null){
            	map	.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 04.0f));
            }
        }
    };
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			mDrawerLayout.closeDrawer(navList);
			navClic(position);
		}
	}
	private void navClic(int pos){
		switch(pos) {
		case 0: 
			Intent intent = new Intent(this, PerfilActivity.class);
			intent.putExtra("personaje", personaje);
			startActivity(intent);
			finish();
			break;
		case 1:
//			Intent intent = new Intent(this, MapActivity.class);
//			intent.putExtra("personaje", personaje);
//			startActivity(intent);
//			finish();
			break;
		case 2: 
			Intent intentt = new Intent(this, ListaActivity.class);
			intentt.putExtra("personaje", personaje);
			startActivity(intentt);
			finish();
			break;
		case 3: 
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
    
