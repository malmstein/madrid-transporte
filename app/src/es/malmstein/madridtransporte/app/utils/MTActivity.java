package es.malmstein.madridtransporte.app.utils;

import android.os.Bundle;

import com.actionbarsherlock.R;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MTActivity extends SherlockFragmentActivity{

	public static int THEME = R.style.Theme_Sherlock;
	
    @Override
    protected void onCreate(Bundle arg0) {
    	setTheme(THEME);
        super.onCreate(arg0);
    }

}
