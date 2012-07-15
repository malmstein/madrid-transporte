package es.malmstein.madridtransporte.app.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import es.malmstein.madridtransporte.app.fragments.BusCercanasFragment;
import es.malmstein.madridtransporte.app.fragments.CercaniasCercanasFragment;
import es.malmstein.madridtransporte.app.fragments.MetroCercanasFragment;

public class EstacionesCercanasPagerAdapter extends FragmentPagerAdapter {
	
	protected Context mContext;
		
	public EstacionesCercanasPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public int getCount() {
		return 3;
	}
	
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:             
            	return BusCercanasFragment.newInstance();   
            case 1:                                     
            	return MetroCercanasFragment.newInstance();                  
            case 2:                
            	return CercaniasCercanasFragment.newInstance();                                      
        }
        
        return null;
    }
}
