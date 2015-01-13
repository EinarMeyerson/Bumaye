package ea.grupo2.Bumaye.android;

import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BatallaActivity extends Activity {
	
	
	String url;
	BatallaVO batalla;
	PersonajeVO personaje, enemigo;
	String strAtaq,strArma;
	TextView nombre_personaje, ataque_personaje, defensa_personaje;
	private ProgressBar progressBar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_batalla);

		url = (String) getIntent().getExtras().get("url");
//		batalla=(BatallaVO) getIntent().getExtras().get("batalla");
//		if (batalla.getIdbatalla()==0)
//		{
//			Toast.makeText(getApplicationContext(), "Server not active",
//					   Toast.LENGTH_LONG).show();
//			finish();
//		}
		personaje = (PersonajeVO) getIntent().getExtras().get("personaje");
        if (personaje.getIduser()==0)
		{
			Toast.makeText(getApplicationContext(), "Server not active",
					   Toast.LENGTH_LONG).show();
			finish();
		}
        getWindow().setBackgroundDrawableResource(R.drawable.fondomarron);
        
        nombre_personaje = (TextView) findViewById(R.id.personaje_nombre);
        nombre_personaje.setText(personaje.getNombre());
        ataque_personaje = (TextView) findViewById(R.id.persojane_ataque);
        defensa_personaje = (TextView) findViewById(R.id.persojane_defensa);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_vida);
        
        refrescarAtributos(personaje);
        
	}
	
	public void refrescarAtributos (PersonajeVO per)
	{
		progressBar.setProgress((int)per.getVida());
		ataque_personaje.setText("Ataque  "+ Float.toString(per.getAtaque()));
		defensa_personaje.setText("Defensa  "+Float.toString(per.getDefensa()));
	}

}
