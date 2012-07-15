package es.malmstein.madridtransporte.app.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.actionbarsherlock.app.ActionBar;
import com.astuetz.viewpager.extensions.FixedTabsView;

import es.malmstein.madridtransporte.app.R;
import es.malmstein.madridtransporte.app.adapters.CalculaRutaPagerAdapter;
import es.malmstein.madridtransporte.app.adapters.FixedIconTabsAdapter;
import es.malmstein.madridtransporte.app.utils.MTActivity;
import es.malmstein.madridtransporte.app.utils.MTSharedPreference;

public class CalculaRuta extends MTActivity{

	private ViewPager mPager;
	private CalculaRutaPagerAdapter mPagerAdapter;
	private FixedIconTabsAdapter mFixedTabsAdapter;
	private FixedTabsView mFixedTabs;
	
	@Override
	protected void onCreate(Bundle arg0) {	
		super.onCreate(arg0);
		
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
		
		setContentView(R.layout.activity_calcula_ruta);
		
		mPager = (ViewPager) findViewById(R.id.calcula_ruta_pager);
		mPagerAdapter = new CalculaRutaPagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);	
		mPager.setCurrentItem(MTSharedPreference.getInstance().getPreferenceInt(MTSharedPreference.PREFERENCES.DEFAULT_CALCULA_RUTA));		
		
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				MTSharedPreference.getInstance().setPreferenceInt(MTSharedPreference.PREFERENCES.DEFAULT_CALCULA_RUTA, arg0);		
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});		
		
		mFixedTabs = (FixedTabsView) findViewById(R.id.calcula_ruta_icon_tabs);
		mFixedTabsAdapter = new FixedIconTabsAdapter(this);
		mFixedTabs.setAdapter(mFixedTabsAdapter);
		mFixedTabs.setViewPager(mPager);
	}
}
