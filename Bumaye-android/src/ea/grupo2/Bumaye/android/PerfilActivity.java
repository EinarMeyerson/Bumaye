package ea.grupo2.Bumaye.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;

public class PerfilActivity extends Activity {

	private ListView navList;
	private DrawerLayout mDrawerLayout;
	TableLayout table_layout;
	String[] lista_atributos= new String[6];//para crear las filas de la tabla de ataques
	String url;
	PersonajeVO personaje;
	String strAtaq,strArma;
	TextView ataque, defensa;
	ImageView cascoimagen, guantesimagen, corazaimagen, armaimagen, piernasimagen, botasimagen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);

		url = (String) getIntent().getExtras().get("url");
		personaje = (PersonajeVO) getIntent().getExtras().get("personaje");
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

		ataque = (TextView) findViewById(R.id.ataque);
		defensa = (TextView) findViewById(R.id.defensa);
		
		//se define la tabla de ataques
		 table_layout = (TableLayout) findViewById(R.id.tableataques);

		//se inicializan las imagenes para cargar las armaduras
		cascoimagen = (ImageView)findViewById(R.id.casco);
		guantesimagen = (ImageView)findViewById(R.id.guantes);
		corazaimagen = (ImageView)findViewById(R.id.coraza); 
		armaimagen= (ImageView)findViewById(R.id.arma); 
		piernasimagen = (ImageView)findViewById(R.id.piernas); 
		botasimagen = (ImageView)findViewById(R.id.botas); 

		refreshPerfil();
	}    
	private void refreshPerfil(){

		ataque.setText("\nAtaque: "+personaje.getAtaque());
		defensa.setText("Defensa: "+personaje.getDefensa());
		
		//inicializamos la primera fial de la tabla
		lista_atributos[0]="Nombre";
		lista_atributos[1]="Daño";
		lista_atributos[2]="Acierto";
		lista_atributos[3]="Afecta a";
		lista_atributos[4]="Cantidad";
		lista_atributos[5]="Afectado";
		BuildTable(lista_atributos);
		
		//inicializar la primera fial de la tabla

		for (ArmaArmaduraVO arm: personaje.getArmasarmaduras()) {
			
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
				//Añadir filas a la tabla
				//ataques.setText(ataques.getText()+ atac.getNombreataque()+": Daño "  + atac.getFactor() + ", acierto "+ atac.getAcierto() + "%, Atributo " + atac.getAtributoafectado() + ", Veces de uso: " + atac.getVecesuso() + ", Afecta a " + afectado + "\n");

				lista_atributos[0]=atac.getNombreataque();
				lista_atributos[1]=Float.toString(atac.getFactor());
				lista_atributos[2]=Float.toString(atac.getAcierto());
				lista_atributos[3]=atac.getAtributoafectado();
				lista_atributos[4]=Float.toString(atac.getVecesuso());
				lista_atributos[5]=afectado;
				BuildTable(lista_atributos);
			}

			Uri uri = Uri.parse("android.resource://ea.grupo2.Bumaye.android/drawable/"+arm.getNombre());


			if (arm.getTipo().equals("arma"))
			{
				armaimagen.setImageURI(uri);

			}else if(arm.getTipo().equals("casco"))
			{
				cascoimagen.setImageURI(uri);

			}else if(arm.getTipo().equals("guantes"))
			{
				guantesimagen.setImageURI(uri);

			}else if(arm.getTipo().equals("coraza"))
			{
				corazaimagen.setImageURI(uri);

			}else if(arm.getTipo().equals("perneras"))
			{
				piernasimagen.setImageURI(uri);

			}else if(arm.getTipo().equals("botas"))
			{
				//botas
				botasimagen.setImageURI(uri);
			}


		}
		//    	ataque.setText(ataque.getText() + "cuerpo a cuerpo : + 10"+ "\n");
		//    	defensa.setText(defensa.getText() + "cuerpo a cuerpo : + 10"+ "\n");
		//    	ataque.setText(ataque.getText() + "-----------------------"+ "\n");
		//    	defensa.setText(defensa.getText() + "-----------------------"+ "\n");
		//		ataque.setText(ataque.getText() +"Total: "+ personaje.getAtaque()+ "\n");
		//    	defensa.setText(defensa.getText() +"Total: "+ personaje.getDefensa()+ "\n");
	}

	public void clickImagen(View view){

		if (view.getId()==R.id.casco)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				AtaqueVO ataces = new AtaqueVO();

				for (AtaqueVO atac: arm.getAtaques()) {

					ataces=atac;
				}

				if (arm.getTipo().equals("casco")){
					String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
					Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
				}
			}
		}
		if (view.getId()==R.id.guantes)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				AtaqueVO ataces = new AtaqueVO();

				for (AtaqueVO atac: arm.getAtaques()) {

					ataces=atac;
				}

				if (arm.getTipo().equals("guantes")){
					String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
					Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
				}
			}
		}
		if (view.getId()==R.id.coraza)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				AtaqueVO ataces = new AtaqueVO();

				for (AtaqueVO atac: arm.getAtaques()) {

					ataces=atac;
				}

				if (arm.getTipo().equals("coraza")){
					String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
					Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
				}
			}
		}
		if (view.getId()==R.id.arma)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				AtaqueVO ataces = new AtaqueVO();

				for (AtaqueVO atac: arm.getAtaques()) {

					ataces=atac;
				}

				if (arm.getTipo().equals("arma")){
					String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
					Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
				}
			}
		}
		if (view.getId()==R.id.piernas)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				AtaqueVO ataces = new AtaqueVO();

				for (AtaqueVO atac: arm.getAtaques()) {

					ataces=atac;
				}

				if (arm.getTipo().equals("perneras")){
					String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
					Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
				}
			}
		}
		if (view.getId()==R.id.botas)
		{
			for(ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				AtaqueVO ataces = new AtaqueVO();

				for (AtaqueVO atac: arm.getAtaques()) {

					ataces=atac;
				}

				if (arm.getTipo().equals("botas")){
					String especificaciones ="Nombre : " + arm.getNombre()+ "\n Ataque : " + arm.getAtaque() + "\n Defensa: " + arm.getDefensa() + "\n Ataque : " + ataces.getNombreataque();
					Toast.makeText(PerfilActivity.this, especificaciones , Toast.LENGTH_LONG).show();
				}
			}
		}

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
			Intent intent = new Intent(this, MapActivity.class);
			intent.putExtra("personaje", personaje);
			startActivity(intent);
			break;
		case 2: 
			Intent intentt = new Intent(this, ListaActivity.class);
			intentt.putExtra("personaje", personaje);
			startActivity(intentt);
			break;
		case 3: 
			break;
		default: 
			break;
		}
	}
	
	
	//funcion para crear nuevas entradas en la tabla de ataques
	private void BuildTable(String[] atributos) {
		   TableRow row = new TableRow(this);
		   // crea seis columnas 
		   for (int j = 0; j <= 5; j++) {

		    TextView tv = new TextView(this);
		    tv.setText(atributos[j]);
		    row.addView(tv);

		   }
		   table_layout.addView(row);
		 }
}

