package ea.grupo2.Bumaye.android;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.JugadorAdapter;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;

public class ListaActivity extends ListActivity {
	private final static String TAG = ListaActivity.class.getName();
	ArrayList<PersonajeVO> PersonList = new ArrayList<PersonajeVO>();
	private JugadorAdapter adapter;
	String url;
	private UsrPersonajeAPI api;
    PopupWindow popUp;
	int idplayer;
	PersonajeVO personaje;
	String nom;
	String serverAddress;
	String serverPort;
	private ListView navList;
	private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        SharedPreferences prefs = getSharedPreferences("upc.eetac.ea.bumaye",Context.MODE_PRIVATE); 
		nom = prefs.getString("nombre", "");
		
        idplayer = getIntent().getExtras().getInt("idplayer");
        api = new UsrPersonajeAPI();
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
					+ "/Bumaye-api/user/lista/"+idplayer;
		 mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

			// Load an array of options names       
			String[] names = getResources().getStringArray(
					R.array.nav_options);
			if (nom!=null)
				names[0] = Html.fromHtml("<b>"+nom+"</b>").toString();
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
			addNotas(result);

			if (pd != null) {
				pd.dismiss();
			}
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(ListaActivity.this);
			pd.setTitle("Loading...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}

	}

	private void addNotas(List<PersonajeVO> result) {
		PersonList.addAll(result);
		adapter = new JugadorAdapter(this, result);
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		PersonajeVO pers = PersonList.get(position);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		final String nom = pers.getNombre();
		builder.setTitle("Lucha contra: "+pers.getNombre());
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User clicked OK button
		        	   dialog.cancel();
		       			inicioBatalla(nom);
		           }
		       });
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
			               // User cancelled the dialog
		        	   dialog.cancel();
		           }
		       });		
		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}
private void inicioBatalla(String nombrerival){
		Intent intent = new Intent(this,LuchaActivity.class);
		intent.putExtra("nomjug", nombrerival);
	   startActivity(intent);
	   finish();	
}
private class DrawerItemClickListener implements ListView.OnItemClickListener {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		mDrawerLayout.closeDrawer(navList);
		navClic(position);
	}
}
private void navClic(int pos){
	switch(pos) {            
	case 1: 

		break;
	case 2: 
		
		break;
	case 3: 
		break;
	default: 
		break;
	}
}
}
    
