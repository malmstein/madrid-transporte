package es.malmstein.madridtransporte.app;

import android.app.Application;
import es.malmstein.madridtransporte.app.utils.MTSharedPreference;
import es.malmstein.madridtransporte.library.model.MTEngine;

public class MTApp extends Application{

	private static MTApp sInstance;
	
	public static MTApp getInstance(){
		return sInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		sInstance = this;
		
		MTEngine.startInstance(this);
        MTSharedPreference.startInstance(this);
		
	}
	
   @Override
   public void onTerminate() {
	   MTEngine.stopInstance();
	   MTSharedPreference.stopInstance();
	        
	   super.onTerminate();	
   }
   
}
