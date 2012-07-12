package es.malmstein.madridtransporte.app.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.astuetz.viewpager.extensions.TabsAdapter;
import com.astuetz.viewpager.extensions.ViewPagerTabButton;

import es.malmstein.madridtransporte.app.R;

public class FixedIconTabsAdapter implements TabsAdapter {
	
	private Activity mContext;
	
	private int[] mIcons = {
	    R.drawable.groups, R.drawable.contacts, R.drawable.favourites
	};
	
	public FixedIconTabsAdapter(Activity ctx) {
		this.mContext = ctx;
	}
	
	@Override
	public View getView(int position) {
		ViewPagerTabButton tab;
		
		LayoutInflater inflater = mContext.getLayoutInflater();
		tab = (ViewPagerTabButton) inflater.inflate(R.layout.tab_fixed_icon, null);
		
		if (position < mIcons.length) tab.setCompoundDrawablesWithIntrinsicBounds(null, mContext.getResources().getDrawable(mIcons[position]), null, null);
		
		return tab;
	}
	
}
