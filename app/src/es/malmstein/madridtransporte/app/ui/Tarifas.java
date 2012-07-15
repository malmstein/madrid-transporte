package es.malmstein.madridtransporte.app.ui;

import com.actionbarsherlock.app.ActionBar;

import android.os.Bundle;
import es.malmstein.madridtransporte.app.utils.MTActivity;

public class Tarifas extends MTActivity{

	@Override
	protected void onCreate(Bundle arg0) {	
		super.onCreate(arg0);
		
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
		
	}
}
