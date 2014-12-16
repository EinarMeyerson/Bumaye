package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.PersonAdapter;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;

public class MapActivity extends FragmentActivity{

	String url;
	PersonajeVO personaje;
	String strAtaq,strArma;
	private UsrPersonajeAPI api;
	String serverAddress;
	String serverPort;
	int iduser;
	private ArrayList<PersonajeVO> PersonList;
	private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
		iduser = (Integer) getIntent().getExtras().get("iduser");

		api = new UsrPersonajeAPI();
		AssetManager assetManager = getAssets();
		Properties config = new Properties();		
			try {
				config.load(assetManager.open("config.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			serverAddress = config.getProperty("server.address");
			serverPort = config.getProperty("server.port");
			
		url = "http://" + serverAddress + ":" + serverPort
				+ "/Bumaye-api/user/lista/"+iduser;
		GoogleMap mMap = ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map))
	               .getMap();
		mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
		mMap.setMyLocationEnabled(true);
        PersonList = new ArrayList<PersonajeVO>();
		//adapter = new PersonAdapter(this, PersonList);
		//setListAdapter(adapter);
		//(new FetchPersonsTask()).execute(url);
    }
    private class FetchPersonsTask extends AsyncTask<String, Void, List<PersonajeVO>> {
		private ProgressDialog pd;
	 
		@Override
		protected List<PersonajeVO> doInBackground(String... params) {
			List<PersonajeVO> person = null;
			person = api.getAllPerson(params[0]);	
			
			return person;
		}
	 
		@Override
		protected void onPostExecute(List<PersonajeVO> result) {
			addPersons(result);
			
			if (pd != null) {
				pd.dismiss();
			}
		}
	 
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(MapActivity.this);
			pd.setTitle("Loading...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}
	 
	}
    private void addPersons(List<PersonajeVO> persons){
    	PersonList.clear();
    	PersonList.addAll(persons);
		adapter.notifyDataSetChanged();
	}
}
    
