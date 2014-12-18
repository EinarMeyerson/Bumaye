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
    	
    	for (ArmaArmaduraVO arm: personaje.getArmasarmaduras()) {
    		
//    		ataque.setText(ataque.getText() + arm.getNombre()+" : + "+ arm.getAtaque() + "\n");
//        	defensa.setText(defensa.getText() + arm.getNombre()+" : + "+ arm.getDefensa()+ "\n");        	
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
    	Intent intent = new Intent(this, RegisterActivity.class);   
    	break;
    case 3: 
    	Intent intent2 = new Intent(this, MapActivity.class);   
    	break;
    case 4: 
    	Intent intent3 = new Intent(this, MapActivity.class);
    	break;
    default: 
        break;
    }
}
}
    
