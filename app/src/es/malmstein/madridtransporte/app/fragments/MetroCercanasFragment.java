package es.malmstein.madridtransporte.app.fragments;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import es.malmstein.madridtransporte.app.R;

public class MetroCercanasFragment extends SherlockFragment{

    public static MetroCercanasFragment newInstance() {
    	MetroCercanasFragment f = new MetroCercanasFragment();
        return f;
    }    
    
    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        if (outState.isEmpty()) {
            outState.putBoolean("bug:fix", true);  
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_metro_cercanas, container, false);
        
        return view;
    }
	
}
