package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.Properties;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;

public class LocationService extends Service implements LocationListener{
	private final static String TAG = LocationService.class.getName();
	static final int MIN_TIME = 1000*60;
	static final int MIN_DIST = 100;
	LocationManager lm;    
	private UsrPersonajeAPI api;
	String serverAddress;
	String serverPort;
	String iduser;
	String url;
	double lat,lng;
    @Override
    public void onCreate() {
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
		}
		SharedPreferences prefs = getSharedPreferences("upc.eetac.ea.bumaye",Context.MODE_PRIVATE); 
		iduser = prefs.getString("iduser", "");		
		
    	lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DIST, this);
    }

    @Override
    public void onDestroy() {
       
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		lat = location.getLatitude();
		lng = location.getLongitude();
		url = "http://" + serverAddress + ":" + serverPort
				+ "/Bumaye-api/user/posicionnueva/"+iduser+"/"+lat+"/"+lng;
		locateInBackground();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	private void locateInBackground() {
		new AsyncTask<String, Void, Void>() {
			@Override
			protected Void doInBackground(String... params) {
				api.locateUsr(params[0]);
				return null;								
			}			
		}.execute(null, null, null);
	}
    
}