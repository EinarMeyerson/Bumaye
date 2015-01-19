package ea.grupo2.Bumaye.android;

import java.util.ArrayList;
import java.util.List;

import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
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
	TextView nombre_enemigo, enemigo_ataque, enemigo_defensa;
	ImageView ataque_1, ataque_2, ataque_3, ataque_4, ataque_5, ataque_6;
	ListView lv;
	private ProgressBar progressBar, enemigo_progressBar_vida;
	int progressStatus = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_batalla);

		url = (String) getIntent().getExtras().get("url");
		
		batalla=(BatallaVO) getIntent().getExtras().get("batalla");
		if (batalla.getIdbatalla()==0)
		{
			Toast.makeText(getApplicationContext(), "Server not active",
					Toast.LENGTH_LONG).show();
			finish();
		}
		
		personaje = (PersonajeVO) getIntent().getExtras().get("personaje");
		if (personaje.getIduser()==0)
		{
			Toast.makeText(getApplicationContext(), "Server not active",
					Toast.LENGTH_LONG).show();
			finish();
		}
		getWindow().setBackgroundDrawableResource(R.drawable.fondomarron);
		
		nombre_enemigo= (TextView) findViewById(R.id.enemigo_nombre);
		enemigo_ataque = (TextView) findViewById(R.id.enemigo_ataque);
		enemigo_defensa = (TextView) findViewById(R.id.enemigo_defensa);
		enemigo_progressBar_vida = (ProgressBar) findViewById(R.id.enemigo_progressBar_vida);

		nombre_personaje = (TextView) findViewById(R.id.personaje_nombre);
		ataque_personaje = (TextView) findViewById(R.id.persojane_ataque);
		defensa_personaje = (TextView) findViewById(R.id.persojane_defensa);
		progressBar = (ProgressBar) findViewById(R.id.progressBar_vida);

		ataque_1 = (ImageView)findViewById(R.id.ataque_1);
		ataque_2 = (ImageView)findViewById(R.id.ataque_2);
		ataque_3 = (ImageView)findViewById(R.id.ataque_3); 
		ataque_4= (ImageView)findViewById(R.id.ataque_4); 
		ataque_5 = (ImageView)findViewById(R.id.ataque_5); 
		ataque_6 = (ImageView)findViewById(R.id.ataque_6); 

		lv=(ListView) findViewById(R.id.listObjetos_personaje);
		String [] nombre_Objetos = getnomObjetos(personaje);
		int [] cantidad_Objetos = getcantidadObjetos(personaje);
		lv.setAdapter(new CustomAdapterBatalla(this, nombre_Objetos, cantidad_Objetos));

		refrescarAtributosPersonaje(personaje);
		
		enemigo = batalla.getEnemigo(personaje.getNombre());
		refrescarAtributosEnemigo(enemigo);
		cargarAtaques();


	}

	public void refrescarAtributosEnemigo (PersonajeVO enemi)
	{
		nombre_enemigo.setText(enemi.getNombre());
		enemigo_ataque.setText("Ataque  "+ Float.toString(enemi.getAtaque()));
		enemigo_defensa.setText("Defensa  "+Float.toString(enemi.getDefensa()));
		enemigo_progressBar_vida.setProgress((int)enemi.getVida());

	}


	public void refrescarAtributosPersonaje (PersonajeVO per)
	{
		progressBar.setProgress((int)per.getVida());
		nombre_personaje.setText(per.getNombre());
		ataque_personaje.setText("Ataque  "+ Float.toString(per.getAtaque()));
		defensa_personaje.setText("Defensa  "+Float.toString(per.getDefensa()));


	}

	public String[] getnomObjetos (PersonajeVO person){

		int cantidad=cantidadObjetos();
		List<String> objetosrepetidos= new ArrayList<String>(cantidad);
		String [] nomObjetos = new String[cantidad];
		int i=0;
		for(ObjetoCantidadVO objet: person.getInventario())
		{
			if(objetosrepetidos.contains(objet.getNombre())==false)
			{
				Log.d("TIPO: ",objet.getTipo());
				if (objet.getTipo().equals("vida") || objet.getTipo().equals("ataque") || objet.getTipo().equals("defensa"))
				{
					objetosrepetidos.add(objet.getNombre());
					nomObjetos[i]= objet.getNombre();
					i++;
				}
			}
		}
		return nomObjetos;		
	}

	public int[] getcantidadObjetos (PersonajeVO person){

		int cantidad=cantidadObjetos();
		int [] canObjetos = new int[cantidad];
		List<String> objetosrepetidos= new ArrayList<String>(cantidad);
		int i=0;
		for(ObjetoCantidadVO objet: person.getInventario())
		{
			if(objetosrepetidos.contains(objet.getNombre())==false)
			{
				if (objet.getTipo().equals("vida") || objet.getTipo().equals("ataque") || objet.getTipo().equals("defensa"))
				{
					objetosrepetidos.add(objet.getNombre());
					canObjetos[i]= objet.getCantidad();
					i++;
				}
			}

		}
		return canObjetos;		
	}

	public int cantidadObjetos ()
	{
		int cantidad=0;
		List<String> objetosrepetidos= new ArrayList<String>(cantidad);
		for(ObjetoCantidadVO objet: personaje.getInventario())
		{
			if(objetosrepetidos.contains(objet.getNombre())==false)
			{
				if(objet.getTipo().equals("vida") || objet.getTipo().equals("ataque") || objet.getTipo().equals("defensa"))
				{
					objetosrepetidos.add(objet.getNombre());
				}
			}

		}
		cantidad=cantidad+objetosrepetidos.size();

		return cantidad;
	}

	public void cargarAtaques ()
	{
		int contador_ataques=0;

		for (ArmaArmaduraVO arm: personaje.getArmasarmaduras()) {

			//solo la pinta si esta equipada
			if (arm.getEquipada()==1){

				for (AtaqueVO atac: arm.getAtaques()) {

					if(atac!=null)
					{
						Uri uri = Uri.parse("android.resource://ea.grupo2.Bumaye.android/drawable/"+arm.getNombre());


						if (contador_ataques==0)
						{
							ataque_1.setImageURI(uri);
							contador_ataques++;

						}else if(contador_ataques==1)
						{
							ataque_2.setImageURI(uri);
							contador_ataques++;

						}else if(contador_ataques==2)
						{
							ataque_3.setImageURI(uri);
							contador_ataques++;

						}else if(contador_ataques==3)
						{
							ataque_4.setImageURI(uri);
							contador_ataques++;

						}else if(contador_ataques==4)
						{
							ataque_5.setImageURI(uri);
							contador_ataques++;

						}else if(contador_ataques==5)
						{
							ataque_6.setImageURI(uri);
							contador_ataques++;
						}
					}


				}


			}


		}
	}

	public void clickAtaque(View view){

		if (view.getId()==R.id.casco)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				if (arm.getEquipada()==1){
					AtaqueVO ataces = new AtaqueVO();

					for (AtaqueVO atac: arm.getAtaques()) {

						ataces=atac;
					}

					if (arm.getTipo().equals("casco")){
						if(ataces.getNombreataque()==null){
							ataces.setNombreataque("sin ataque");
						}
						String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
						//Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
					}
				}
			}
		}
		if (view.getId()==R.id.guantes)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				if (arm.getEquipada()==1){
					AtaqueVO ataces = new AtaqueVO();

					for (AtaqueVO atac: arm.getAtaques()) {

						ataces=atac;
					}

					if (arm.getTipo().equals("guantes")){
						if(ataces.getNombreataque()==null){
							ataces.setNombreataque("sin ataque");
						}
						String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
						//Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
					}
				}
			}
		}
		if (view.getId()==R.id.coraza)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				if (arm.getEquipada()==1){
					AtaqueVO ataces = new AtaqueVO();

					for (AtaqueVO atac: arm.getAtaques()) {

						ataces=atac;
					}

					if (arm.getTipo().equals("coraza")){
						if(ataces.getNombreataque()==null){
							ataces.setNombreataque("sin ataque");
						}
						String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
						//Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
					}
				}
			}
		}
		if (view.getId()==R.id.arma)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				if (arm.getEquipada()==1){
					AtaqueVO ataces = new AtaqueVO();

					for (AtaqueVO atac: arm.getAtaques()) {

						ataces=atac;
					}

					if (arm.getTipo().equals("arma")){
						if(ataces.getNombreataque()==null){
							ataces.setNombreataque("sin ataque");
						}
						String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
						//Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
					}
				}
			}
		}
		if (view.getId()==R.id.piernas)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				if (arm.getEquipada()==1){
					AtaqueVO ataces = new AtaqueVO();

					for (AtaqueVO atac: arm.getAtaques()) {

						ataces=atac;
					}

					if (arm.getTipo().equals("perneras")){
						if(ataces.getNombreataque()==null){
							ataces.setNombreataque("sin ataque");
						}
						String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
						//Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
					}
				}
			}
		}
		if (view.getId()==R.id.botas)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				if (arm.getEquipada()==1){
					AtaqueVO ataces = new AtaqueVO();

					for (AtaqueVO atac: arm.getAtaques()) {

						ataces=atac;
					}

					if (arm.getTipo().equals("botas")){
						if(ataces.getNombreataque()==null){
							ataces.setNombreataque("sin ataque");
						}
						String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
						//Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
					}
				}
			}
		}

	}

}
