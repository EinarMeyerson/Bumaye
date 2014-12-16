package ea.grupo2.Bumaye.android;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.JugadorAdapter;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;

public class ListaActivity extends ListActivity {
	private final static String TAG = ListaActivity.class.getName();
	ArrayList<PersonajeVO> PersonList = new ArrayList<PersonajeVO>();
	private JugadorAdapter adapter;
	String url;
	private UsrPersonajeAPI api;
	int idplayer;
	PersonajeVO personaje;
	String serverAddress;
	String serverPort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
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
		
	}

}
    
