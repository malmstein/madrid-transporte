package es.malmstein.madridtransporte.app.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import es.malmstein.madridtransporte.app.fragments.BusMainFragment;
import es.malmstein.madridtransporte.app.fragments.CercaniasMainFragment;
import es.malmstein.madridtransporte.app.fragments.MetroMainFragment;

public class CalculaRutaPagerAdapter extends FragmentPagerAdapter {
	
	protected Context mContext;
		
	public CalculaRutaPagerAdapter(FragmentManager fm) {
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
            	return BusMainFragment.newInstance();   
            case 1:                                     
            	return MetroMainFragment.newInstance();                  
            case 2:                
            	return CercaniasMainFragment.newInstance();                                      
        }
        
        return null;
    }
}
