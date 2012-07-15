package es.malmstein.madridtransporte.app.ui;

import java.util.ArrayList;

import tasks.ParseMetroDataAsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import es.malmstein.madridtransporte.app.R;
import es.malmstein.madridtransporte.library.model.MTConstants;
import es.malmstein.madridtransporte.library.model.MTEngine;
import es.malmstein.madridtransporte.library.objects.MetroLine;

public class SplashScreen extends FragmentActivity{
	
	private boolean cargando = false;
	private ParseMetroDataAsyncTask metroDataTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
	    Bundle localBundle = (Bundle)getLastCustomNonConfigurationInstance();
	    Object localObject = new Object();
	    
	    if (localBundle != null){
	    	cargando = localBundle.getBoolean(MTConstants.INTENT_EXTRAS.LOADING);
	    	localObject = localBundle.getSerializable(MTConstants.INTENT_EXTRAS.ASYNC_TASK);	    
	    }
	    
	    ArrayList<MetroLine> localMetroList = MTEngine.getInstance().getLocalMetroLines();
	    if (localMetroList.size() > 0){
		    if ((localObject != null) && (localObject instanceof ParseMetroDataAsyncTask)){
		    	this.metroDataTask = ((ParseMetroDataAsyncTask)localObject);
			    this.metroDataTask.relink(this);				    
		    }else{
		    	loadMainActivity();
		    }
	    }else{
	    	this.metroDataTask = new ParseMetroDataAsyncTask(this);
	    	this.metroDataTask.execute(new Void[0]);
	    	cargando = true;
	    }	    	   
	    
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		
		Object localObject;
	    if (this.metroDataTask != null) {
	    	this.metroDataTask.unlink();	    	
	    	
	    	Bundle localBundle = new Bundle();
	    	localBundle.putBoolean(MTConstants.INTENT_EXTRAS.LOADING, cargando);
	    	localBundle.putSerializable(MTConstants.INTENT_EXTRAS.ASYNC_TASK, this.metroDataTask);
	    	localObject = localBundle;
	    }else{	    
		    localObject = super.onRetainNonConfigurationInstance();
	    }		
	    
	    return localObject;
	}
	
	private void loadMainActivity(){
		Intent mainIntent = new Intent(this, MainActivity.class);
		startActivity(mainIntent);
		finish();
	}
}
