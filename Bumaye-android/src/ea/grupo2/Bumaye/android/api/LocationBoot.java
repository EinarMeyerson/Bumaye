package ea.grupo2.Bumaye.android.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import ea.grupo2.Bumaye.android.LocationService;

public class LocationBoot extends BroadcastReceiver {   

    @Override
    public void onReceive(Context context, Intent intent) {

    	Intent myIntent = new Intent(context, LocationService.class);
		context.startService(myIntent);		
    }
}