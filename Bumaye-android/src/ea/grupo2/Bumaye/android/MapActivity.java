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
import android.widget.TextView;
import android.widget.Toast;
import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import android.support.v4.app.FragmentActivity;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;

public class MapActivity extends Activity {

	private ListView navList;
    private DrawerLayout mDrawerLayout;
	String url;
	PersonajeVO personaje =null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        url = (String) getIntent().getExtras().get("url");
		personaje = (PersonajeVO) getIntent().getExtras().get("personaje");

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
    case 2: 
    	Intent intent = new Intent(this, MapActivity.class);   
    	break;
    case 3: 
    	Intent intent2 = new Intent(this, ListaActivity.class);   
    	break;
    case 4: 
    	break;
    default: 
        break;
    }
}
}
    
