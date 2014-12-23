package ea.grupo2.Bumaye.android;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;

public class InventarioActivity extends Activity {

	private ListView navList;
	private DrawerLayout mDrawerLayout;
	String url;
	PersonajeVO personaje;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventario);

		url = (String) getIntent().getExtras().get("url");
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
			finish();
			break;
		case 2: 
			Intent intentt = new Intent(this, ListaActivity.class);
			intentt.putExtra("personaje", personaje);
			startActivity(intentt);
			finish();
			break;
		case 3: 
			Intent intenttt = new Intent(this, InventarioActivity.class);
			intenttt.putExtra("personaje", personaje);
			startActivity(intenttt);
			finish();
			break;
		default: 
			break;
		}
	}
}

