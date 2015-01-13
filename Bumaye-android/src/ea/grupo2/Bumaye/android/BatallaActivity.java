package ea.grupo2.Bumaye.android;

import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
	ImageView ataque_1, ataque_2, ataque_3, ataque_4, ataque_5, ataque_6;
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
        
        ataque_1 = (ImageView)findViewById(R.id.ataque_1);
        ataque_2 = (ImageView)findViewById(R.id.ataque_2);
        ataque_3 = (ImageView)findViewById(R.id.ataque_3); 
		ataque_4= (ImageView)findViewById(R.id.ataque_4); 
		ataque_5 = (ImageView)findViewById(R.id.ataque_5); 
		ataque_6 = (ImageView)findViewById(R.id.ataque_6); 
        
        refrescarAtributos(personaje);
        
	}
	
	public void refrescarAtributos (PersonajeVO per)
	{
		progressBar.setProgress((int)per.getVida());
		ataque_personaje.setText("Ataque  "+ Float.toString(per.getAtaque()));
		defensa_personaje.setText("Defensa  "+Float.toString(per.getDefensa()));
	}
	
	public void cargarAtaques (PersonajeVO per)
	{
		
		for (ArmaArmaduraVO arm: personaje.getArmasarmaduras()) {


			//solo la pinta si esta equipada
			if (arm.getEquipada()==1){

				for (AtaqueVO atac: arm.getAtaques()) {

					String afectado;
					if (atac.getJugadorafectado() == 1)
					{
						afectado = "Enemigo";
					}
					else 
					{	
						afectado = personaje.getNombre();
					}
					
					
				}

				Uri uri = Uri.parse("android.resource://ea.grupo2.Bumaye.android/drawable/"+arm.getNombre());


//				if (arm.getTipo().equals("arma"))
//				{
//					armaimagen.setImageURI(uri);
//
//				}else if(arm.getTipo().equals("casco"))
//				{
//					cascoimagen.setImageURI(uri);
//
//				}else if(arm.getTipo().equals("guantes"))
//				{
//					guantesimagen.setImageURI(uri);
//
//				}else if(arm.getTipo().equals("coraza"))
//				{
//					corazaimagen.setImageURI(uri);
//
//				}else if(arm.getTipo().equals("perneras"))
//				{
//					piernasimagen.setImageURI(uri);
//
//				}else if(arm.getTipo().equals("botas"))
//				{
//					//botas
//					botasimagen.setImageURI(uri);
//				}

			}


		}
	}

}
