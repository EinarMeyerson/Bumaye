package ea.grupo2.Bumaye.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;

public class InventarioActivity extends Activity {

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

		personaje = (PersonajeVO) getIntent().getExtras().get("personaje");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        getWindow().setBackgroundDrawableResource(R.drawable.fondomarron);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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

		//se define la tabla de ataques
		 table_layout = (TableLayout) findViewById(R.id.tableinvent);

		refreshPerfil();
	}    
	private void refreshPerfil(){

		//inicializamos la primera fial de la tabla
		lista_atributos[0]="Nombre";
		lista_atributos[1]="Ataque";
		lista_atributos[2]="Defensa";
		lista_atributos[3]="Lista de ataques";
		BuildTable(lista_atributos);
		
		//inicializar la primera fial de la tabla

		for (ArmaArmaduraVO arm: personaje.getArmasarmaduras()) {						
				
				//Añadir filas a la tabla
				//ataques.setText(ataques.getText()+ atac.getNombreataque()+": Daño "  + atac.getFactor() + ", acierto "+ atac.getAcierto() + "%, Atributo " + atac.getAtributoafectado() + ", Veces de uso: " + atac.getVecesuso() + ", Afecta a " + afectado + "\n");

				lista_atributos[0]=arm.getNombre();
				lista_atributos[1]=Float.toString(arm.getAtaque());
				lista_atributos[2]=Float.toString(arm.getDefensa());
				for (AtaqueVO atac: arm.getAtaques()) {
					lista_atributos[3]=atac.getNombreataque();
				}
				BuildTable(lista_atributos);		

		}
//		lista_atributos[0]="Nombre";
//		BuildTable(lista_atributos);
//		for (ObjetoVO object: personaje.getInventario()) {
//			lista_atributos[0]=object.getNombre();
//			BuildTable(lista_atributos);
//
//		}
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
		case 0: 
			Intent intent = new Intent(this, PerfilActivity.class);
			intent.putExtra("personaje", personaje);
			startActivity(intent);
			finish();
			break;
		case 1:
			Intent intentt = new Intent(this, MapActivity.class);
			intentt.putExtra("personaje", personaje);
			startActivity(intentt);
			finish();
			break;
		case 2: 
			Intent intenttt = new Intent(this, ListaActivity.class);
			intenttt.putExtra("personaje", personaje);
			startActivity(intenttt);
			finish();
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
		    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
		    row.addView(tv);

		   }
		   table_layout.addView(row);
		 }
}

