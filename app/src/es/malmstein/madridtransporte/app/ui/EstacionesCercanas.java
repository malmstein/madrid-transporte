package es.malmstein.madridtransporte.app.ui;

import com.actionbarsherlock.app.ActionBar;
import com.astuetz.viewpager.extensions.FixedTabsView;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import es.malmstein.madridtransporte.app.R;
import es.malmstein.madridtransporte.app.adapters.CalculaRutaPagerAdapter;
import es.malmstein.madridtransporte.app.adapters.FixedIconTabsAdapter;
import es.malmstein.madridtransporte.app.utils.MTActivity;
import es.malmstein.madridtransporte.app.utils.MTSharedPreference;

public class EstacionesCercanas extends MTActivity{

	private ViewPager mPager;
	private CalculaRutaPagerAdapter mPagerAdapter;
	private FixedIconTabsAdapter mFixedTabsAdapter;
	private FixedTabsView mFixedTabs;
	
	@Override
	protected void onCreate(Bundle arg0) {	
		super.onCreate(arg0);
		
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
		
		setContentView(R.layout.activity_estaciones_cercanas);
		
		mPager = (ViewPager) findViewById(R.id.estaciones_cercanas_pager);
		mPagerAdapter = new CalculaRutaPagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);	
		mPager.setCurrentItem(MTSharedPreference.getInstance().getPreferenceInt(MTSharedPreference.PREFERENCES.DEFAULT_ESTACIONES_CERCANAS));		
		
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				MTSharedPreference.getInstance().setPreferenceInt(MTSharedPreference.PREFERENCES.DEFAULT_ESTACIONES_CERCANAS, arg0);		
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});		
		
		mFixedTabs = (FixedTabsView) findViewById(R.id.estaciones_cercanas_icon_tabs);
		mFixedTabsAdapter = new FixedIconTabsAdapter(this);
		mFixedTabs.setAdapter(mFixedTabsAdapter);
		mFixedTabs.setViewPager(mPager);
	}
}
