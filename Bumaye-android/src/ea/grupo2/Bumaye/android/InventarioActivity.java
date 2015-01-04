package ea.grupo2.Bumaye.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.Log;
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
import android.widget.Toast;
import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.android.api.UsrPersonajeAPI;

public class InventarioActivity extends Activity {

	private final static String TAG = LoginActivity.class.getName();
	private ListView navList;
	private DrawerLayout mDrawerLayout;
	String url;
	String serverAddress;
	String serverPort;
	PersonajeVO personaje;
	ListView lv;
	Context context;
	TextView nombreObjeto, rareza, exito, combo1, combo2, tipo, cantidad;
	ImageView imgObjeto;
	Button equipar;
	private UsrPersonajeAPI api;
	public static int [] imgObjetos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventario_prueva);

		api = new UsrPersonajeAPI();

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

		//preparar la url para los equipamientos
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

		//inicializamos el ListView
		lv=(ListView) findViewById(R.id.listObjetos);
		String [] nombre_Objetos = getnomObjetos(personaje);
		int [] cantidad_Objetos = getcantidadObjetos(personaje);
		lv.setAdapter(new CustomAdapter(this, nombre_Objetos, cantidad_Objetos));



		nombreObjeto = (TextView) findViewById(R.id.nombre_objeto_especifico);
		rareza = (TextView) findViewById(R.id.rareza);
		exito = (TextView) findViewById(R.id.exito);
		combo1 = (TextView) findViewById(R.id.combo1);
		combo2 = (TextView) findViewById(R.id.combo2);
		tipo = (TextView) findViewById(R.id.tipo);
		//cantidad= (TextView) findViewById(R.id.cantidad);


		imgObjeto = (ImageView)findViewById(R.id.imagens_objeto_especifica);

		equipar = (Button)findViewById(R.id.equipar_objeto);
		equipar.setVisibility(View.INVISIBLE);


	} 

	//funcion para sacar la lista de nombres de los objetosad

	public String[] getnomObjetos (PersonajeVO person){

		int cantidad=cantidadObjetos();
		List<String> objetosrepetidos= new ArrayList<String>(cantidad);
		String [] nomObjetos = new String[cantidad];
		int i=0;
		for(ObjetoCantidadVO objet: person.getInventario())
		{
			if(objetosrepetidos.contains(objet.getNombre())==false)
			{
				objetosrepetidos.add(objet.getNombre());
				nomObjetos[i]= objet.getNombre();
				i++;
			}
		}
		for (ArmaArmaduraVO arm: person.getArmasarmaduras())
		{

			nomObjetos[i]= arm.getNombre();
			i++;
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
				objetosrepetidos.add(objet.getNombre());
				canObjetos[i]= objet.getCantidad();
				i++;
			}

		}
		for (ArmaArmaduraVO arm: person.getArmasarmaduras())
		{

			canObjetos[i]= 1;
			i++;
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
				objetosrepetidos.add(objet.getNombre());
			}

		}
		cantidad=cantidad+objetosrepetidos.size();
		for (ArmaArmaduraVO arm: personaje.getArmasarmaduras())
		{

			cantidad++;
		}

		return cantidad;
	}

	//funcion para mostrar los detalles de un objeto
	public void clickObjeto(View view){
		String nomobjeto_esp = (String)view.getTag();
		for (ObjetoCantidadVO objet: personaje.getInventario())
		{
			if (nomobjeto_esp==objet.getNombre())
			{
				nombreObjeto.setText(objet.getNombre());
				rareza.setText("Rareza: "+objet.getRareza());
				exito.setText("Exito de combinacion: "+objet.getExito()+ "%");
				combo1.setText("Combo uno: "+ objet.getCombo1());
				combo2.setText("Combo dos: "+ objet.getCombo2());
				tipo.setText("Tipo: "+ objet.getTipo());
				//cantidad.setText("Cantidad: " + objet.getCantidad());
				Uri uri = Uri.parse("android.resource://ea.grupo2.Bumaye.android/drawable/"+objet.getNombre());
				imgObjeto.setImageURI(uri);
				equipar.setVisibility(View.VISIBLE);
				equipar.setText("Combinar");
			}
		}
		for (ArmaArmaduraVO arm: personaje.getArmasarmaduras())
		{
			if (nomobjeto_esp==arm.getNombre())
			{
				nombreObjeto.setText(arm.getNombre());
				rareza.setText("Ataque: "+arm.getAtaque());
				exito.setText("Defensa: "+arm.getDefensa());
				combo1.setText("");
				combo2.setText("Tipo: ");
				tipo.setText(arm.getTipo());
				//cantidad.setText("Cantidad: " + objet.getCantidad());
				Uri uri = Uri.parse("android.resource://ea.grupo2.Bumaye.android/drawable/"+arm.getNombre());
				imgObjeto.setImageURI(uri);

				if (arm.getEquipada()==1)
				{
					equipar.setVisibility(View.VISIBLE);
					equipar.setText("Desequipar");
				}
				else
				{
					equipar.setVisibility(View.VISIBLE);
					equipar.setText("Equipar");
				}
			}
		}

	}

	public void Equipar_Combinar (View v) {

		String boton = equipar.getText().toString();

		if (boton.equals("Equipar"))
		{
			String nombreArmadura= nombreObjeto.getText().toString();
			String tipoarmadura= tipo.getText().toString();
			int idarmequipada=0;
			int idarmdesequipada=0;

			for (ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				if (nombreArmadura==arm.getNombre()){
					idarmequipada=arm.getIdarmaarmadura();
				}

			}
			for (ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				if ((tipoarmadura.equals(arm.getTipo())) && (arm.getEquipada()==1)){
					idarmdesequipada= arm.getIdarmaarmadura();
				}

			}

			url = "http://" + serverAddress + ":" + serverPort
					+ "/Bumaye-api/user/equipar";
			(new EquiparUsrTask()).execute(Integer.toString(idarmequipada),Integer.toString(idarmdesequipada),url);

		}

		if (boton.equals("Desequipar"))
		{
			String nombreArmadura= nombreObjeto.getText().toString();
			String tipoarmadura= tipo.getText().toString();
			int idarmdesequipada=0;

			for (ArmaArmaduraVO arm: personaje.getArmasarmaduras())
			{
				if (nombreArmadura==arm.getNombre()){
					idarmdesequipada=arm.getIdarmaarmadura();
				}

			}

			url = "http://" + serverAddress + ":" + serverPort
					+ "/Bumaye-api/user/desequipar";
			(new DesequiparUsrTask()).execute(Integer.toString(idarmdesequipada),url);

		}

		if (boton.equals("Combinar"))
		{
			
			final String [] items = getnomObjetos(personaje);
			//creamos el dialog para elegir el objeto con que combianar
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle(nombreObjeto.getText().toString() + " combinar con: ")
			.setCancelable(false)
			.setIcon(null)
			.setSingleChoiceItems(items,0, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialogInterface, int item) {
					url = "http://" + serverAddress + ":" + serverPort
							+ "/Bumaye-api/user/combinacion";
					(new CombinarUsrTask()).execute(nombreObjeto.getText().toString(),items[item],url);
					dialogInterface.dismiss();
				}
			});
			
			builder.create().show(); 
		}

	}

	
	
	private class CombinarUsrTask extends AsyncTask<String, Void, PersonajeVO> {
		private ProgressDialog pd;
		@Override
		protected PersonajeVO doInBackground(String... params) {
			PersonajeVO person  = new PersonajeVO();
			person = api.combinacionObjetos(personaje.getIduser(),params[0], params[1] ,params[2]);			
			return person;
		}

		@Override
		protected void onPostExecute(PersonajeVO result) {
			Log.d("Usuario :",result.getNombre().toString());
			if (pd != null) {
				pd.dismiss();
			}
			if (result.getNombre() != "")
			{
				Log.e(TAG,result.getNombre());
				Equipado(result);
			}
			else{				
				wrongEquipado();
			}		
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(InventarioActivity.this);
			pd.setTitle("Combinando...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}
	}
	
	private class EquiparUsrTask extends AsyncTask<String, Void, PersonajeVO> {
		private ProgressDialog pd;
		@Override
		protected PersonajeVO doInBackground(String... params) {
			PersonajeVO person  = new PersonajeVO();
			person = api.equiparUser(personaje.getIduser(),Integer.parseInt(params[0]),Integer.parseInt(params[1]),params[2]);			
			return person;
		}

		@Override
		protected void onPostExecute(PersonajeVO result) {
			Log.d("Usuario equipado:",result.getNombre().toString());
			if (pd != null) {
				pd.dismiss();
			}
			if (result.getNombre() != "")
			{
				Log.e(TAG,result.getNombre());
				Equipado(result);
			}
			else{				
				wrongEquipado();
			}		
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(InventarioActivity.this);
			pd.setTitle("Equipando");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}
	}

	private class DesequiparUsrTask extends AsyncTask<String, Void, PersonajeVO> {
		private ProgressDialog pd;
		@Override
		protected PersonajeVO doInBackground(String... params) {
			PersonajeVO person  = new PersonajeVO();
			person = api.desequiparUser(personaje.getIduser(),Integer.parseInt(params[0]),params[1]);			
			return person;
		}

		@Override
		protected void onPostExecute(PersonajeVO result) {
			Log.d("Usuario desequipado:",result.getNombre().toString());
			if (pd != null) {
				pd.dismiss();
			}
			if (result.getNombre() != "")
			{
				Log.e(TAG,result.getNombre());
				Equipado(result);
			}
			else{				
				wrongEquipado();
			}		
		}

		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(InventarioActivity.this);
			pd.setTitle("Desequipando");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}
	}

	private void Equipado(PersonajeVO person){

		Intent intent = new Intent(this, InventarioActivity.class);
		intent.putExtra("url", url);
		intent.putExtra("personaje", person);
		startActivity(intent);
		finish();
	}
	private void wrongEquipado(){
		Toast.makeText(getApplicationContext(), "Error al equiparse",
				Toast.LENGTH_LONG).show();
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

