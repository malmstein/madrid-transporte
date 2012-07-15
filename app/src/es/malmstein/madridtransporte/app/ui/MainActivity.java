package es.malmstein.madridtransporte.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import es.malmstein.madridtransporte.app.R;
import es.malmstein.madridtransporte.app.utils.MTActivity;

public class MainActivity extends MTActivity implements OnClickListener {	
	
	private Button ib_calcula_ruta;
	private Button ib_estaciones_cercanas;
	private Button ib_info_emt;
	private Button ib_info_metro;
	private Button ib_info_cercanias;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, R.string.menu_title_favoritos, 0, getString(R.string.menu_title_favoritos))                
        .setIcon(R.drawable.ic_action_preferences)        
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        
        menu.add(0, R.string.menu_title_incidencias, 1, getString(R.string.menu_title_incidencias))                
        .setIcon(R.drawable.ic_action_preferences)        
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        
        menu.add(0, R.string.menu_title_tarifas, 2, getString(R.string.menu_title_tarifas))                
        .setIcon(R.drawable.ic_action_preferences)        
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        
        menu.add(0, R.string.menu_title_about, 3, getString(R.string.menu_title_about))                
        .setIcon(R.drawable.ic_action_preferences)        
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);        
        
        return true;
    }  
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()) {
            case R.string.menu_title_favoritos:
				Intent favoritosIntent = new Intent(MainActivity.this, Favoritos.class);
				startActivity(favoritosIntent);								
                break;
            case R.string.menu_title_incidencias:
				Intent incidenciasIntent = new Intent(MainActivity.this, Incidencias.class);
				startActivity(incidenciasIntent);				
                break;
            case R.string.menu_title_tarifas:
				Intent tarifasIntent = new Intent(MainActivity.this, Tarifas.class);
				startActivity(tarifasIntent);								
                break;
            case R.string.menu_title_about:

                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
			case R.id.ib_calcula_ruta:
				Intent rutaIntent = new Intent(MainActivity.this, CalculaRuta.class);
				startActivity(rutaIntent);
				break;	
			case R.id.ib_estaciones_cercanas:
				Intent cercanasIntent = new Intent(MainActivity.this, EstacionesCercanas.class);
				startActivity(cercanasIntent);
				break;				
			case R.id.ib_info_bus:
				Intent busIntent = new Intent(MainActivity.this, InfoBus.class);
				startActivity(busIntent);				
				break;				
			case R.id.ib_info_cercanias:
				Intent cercaniasIntent = new Intent(MainActivity.this, InfoCercanias.class);
				startActivity(cercaniasIntent);
				break;				
			case R.id.ib_info_metro:
				Intent metroIntent = new Intent(MainActivity.this, InfoMetro.class);
				startActivity(metroIntent);
				break;			
		}
		
	}
  
    private void initUI(){
    	
        setContentView(R.layout.activity_home);          

        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                    	    	
		ib_calcula_ruta = (Button) findViewById(R.id.ib_calcula_ruta);
		ib_estaciones_cercanas = (Button) findViewById(R.id.ib_estaciones_cercanas);
		ib_info_emt = (Button) findViewById(R.id.ib_info_bus);
		ib_info_metro = (Button) findViewById(R.id.ib_info_metro);
		ib_info_cercanias = (Button) findViewById(R.id.ib_info_cercanias);
		
		ib_calcula_ruta.setOnClickListener(this);
		ib_estaciones_cercanas.setOnClickListener(this);
		ib_info_emt.setOnClickListener(this);
		ib_info_cercanias.setOnClickListener(this);
		ib_info_metro.setOnClickListener(this);
		    	
    }

}
