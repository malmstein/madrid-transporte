package es.malmstein.madridtransporte.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

import es.malmstein.madridtransporte.app.R;

public class CercaniasMainFragment extends SherlockFragment{

    public static CercaniasMainFragment newInstance() {
    	CercaniasMainFragment f = new CercaniasMainFragment();
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
        View view = inflater.inflate(R.layout.fragment_cercanias, container, false);
        
        return view;
    }
	
}
