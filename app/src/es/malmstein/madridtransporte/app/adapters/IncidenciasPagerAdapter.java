package es.malmstein.madridtransporte.app.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import es.malmstein.madridtransporte.app.fragments.BusIncidenciasFragment;
import es.malmstein.madridtransporte.app.fragments.MetroIncidenciasFragment;

public class IncidenciasPagerAdapter extends FragmentPagerAdapter {

	protected Context mContext;
	
	public IncidenciasPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public int getCount() {
		return 2;
	}
	
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:             
            	return BusIncidenciasFragment.newInstance();   
            case 1:                                     
            	return MetroIncidenciasFragment.newInstance();                                              
        }
        
        return null;
    }
    
}
