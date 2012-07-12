package es.malmstein.madridtransporte.app.ui;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.astuetz.viewpager.extensions.FixedTabsView;
import com.astuetz.viewpager.extensions.TabsAdapter;

import es.malmstein.madridtransporte.app.R;
import es.malmstein.madridtransporte.app.adapters.FixedIconTabsAdapter;
import es.malmstein.madridtransporte.app.adapters.MainPagerAdapter;
import es.malmstein.madridtransporte.app.utils.MTActivity;
import es.malmstein.madridtransporte.app.utils.MTSharedPreference;

public class MainActivity extends MTActivity{	
	
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	
	private FixedTabsView mFixedTabs;
	private TabsAdapter mFixedTabsAdapter;
	
    public static class NAV_STATE{
    	public final static int BUS = 0;
        public final static int METRO = 1;
        public final static int TRAIN = 2;
    }
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, R.string.menu_title_settings, 0, getString(R.string.menu_title_settings))                
        .setIcon(R.drawable.ic_action_preferences)        
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        
        return true;
    }  
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()) {
            case R.string.menu_title_settings:

                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
  
    private void initUI(){
    	
        setContentView(R.layout.activity_main);          

        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                    	    	
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);	
		mPager.setCurrentItem(MTSharedPreference.getInstance().getPreferenceInt(MTSharedPreference.PREFERENCES.DEFAULT_TRANSPORT));		
		
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				MTSharedPreference.getInstance().setPreferenceInt(MTSharedPreference.PREFERENCES.DEFAULT_TRANSPORT, arg0);		
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});		
		
		mFixedTabs = (FixedTabsView) findViewById(R.id.fixed_icon_tabs);
		mFixedTabsAdapter = new FixedIconTabsAdapter(this);
		mFixedTabs.setAdapter(mFixedTabsAdapter);
		mFixedTabs.setViewPager(mPager);					
		
    	
    }
}
