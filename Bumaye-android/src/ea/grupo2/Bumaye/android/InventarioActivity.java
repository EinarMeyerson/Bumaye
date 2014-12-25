package ea.grupo2.Bumaye.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
	String url;
	PersonajeVO personaje;
	ListView lv;
	Context context;
	TextView nombreObjeto, rareza, exito, combo1, combo2, tipo;
	ImageView imgObjeto;
	Button equipar;
	public static int [] imgObjetos;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventario_prueva);

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
		
		
		//inicializamos el ListView
		 lv=(ListView) findViewById(R.id.listObjetos);
		 String [] nombre_Objetos = getnomObjetos(personaje);
		 lv.setAdapter(new CustomAdapter(this, nombre_Objetos));
		 
		 
		 
		 nombreObjeto = (TextView) findViewById(R.id.nombre_objeto_especifico);
		 rareza = (TextView) findViewById(R.id.rareza);
		 exito = (TextView) findViewById(R.id.exito);
		 combo1 = (TextView) findViewById(R.id.combo1);
		 combo2 = (TextView) findViewById(R.id.combo2);
		 tipo = (TextView) findViewById(R.id.tipo);

			
		 imgObjeto = (ImageView)findViewById(R.id.imagens_objeto_especifica);
		 
		 equipar = (Button)findViewById(R.id.equipar_objeto);
		 equipar.setVisibility(View.INVISIBLE);
		 
		
	} 
	
	//funcion para sacar la lista de nombres de los objetos
	
	public String[] getnomObjetos (PersonajeVO person){
		
		 String [] nomObjetos = new String[20];
		 int i=0;
		 for(ObjetoVO objet: person.getInventario())
		 {
			 nomObjetos[i]= objet.getNombre();
			 i++;
		 }
		 return nomObjetos;		
	}
	
	//funcion para mostrar los detalles de un objeto
	public void clickObjeto(View view){
		String nomobjeto_esp = (String)view.getTag();
		for (ObjetoVO objet: personaje.getInventario())
		{
			if (nomobjeto_esp==objet.getNombre())
			{
				 nombreObjeto.setText(objet.getNombre());
				 rareza.setText("Rareza: "+objet.getRareza());
				 exito.setText("Exito de combinacion: "+objet.getExito()+ "%");
				 combo1.setText("Combo uno: "+ objet.getCombo1());
				 combo2.setText("Combo dos: "+ objet.getCombo2());
				 tipo.setText("Tipo: "+ objet.getTipo());
				 Uri uri = Uri.parse("android.resource://ea.grupo2.Bumaye.android/drawable/"+objet.getNombre());
				 imgObjeto.setImageURI(uri);
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
			Intent intentttt = new Intent(this, InventarioActivity.class);
			intentttt.putExtra("personaje", personaje);
			startActivity(intentttt);
			finish();
			break;
		default: 
			break;
		}
	}
	
}

