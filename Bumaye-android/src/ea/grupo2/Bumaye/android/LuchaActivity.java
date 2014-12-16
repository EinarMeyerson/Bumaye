package ea.grupo2.Bumaye.android;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;

public class LuchaActivity extends FragmentActivity {

	
	String url;
	PersonajeVO personaje;
	String nombre1,nombre2;
	TextView jug1,jug2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucha);
		nombre1 = (String) getIntent().getExtras().get("nomjug");
		SharedPreferences prefs = getSharedPreferences("upc.eetac.ea.bumaye",Context.MODE_PRIVATE); 
		nombre2 = prefs.getString("nombre", "");
		
		jug1 = (TextView)findViewById(R.id.player1);
		jug2 = (TextView)findViewById(R.id.player2);
		jug1.setText(nombre1);
		jug2.setText(nombre2);
        
    }  


}
    
