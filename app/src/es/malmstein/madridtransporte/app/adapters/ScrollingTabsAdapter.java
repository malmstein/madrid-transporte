package es.malmstein.madridtransporte.app.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.astuetz.viewpager.extensions.TabsAdapter;

import es.malmstein.madridtransporte.app.R;

public class ScrollingTabsAdapter implements TabsAdapter {
	
	private Activity mContext;
	
	private String[] mTitles = {
	    "RUTA", "LINIAS", "MAPA", "FAVORITOS", "INCIDENCIAS", "TARIFAS"
	};
	
	public ScrollingTabsAdapter(Activity ctx) {
		this.mContext = ctx;
	}
	
	@Override
	public View getView(int position) {
		Button tab;
		
		LayoutInflater inflater = mContext.getLayoutInflater();
		tab = (Button) inflater.inflate(R.layout.tab_scrolling, null);
		
		if (position < mTitles.length) tab.setText(mTitles[position]);
		
		return tab;
	}
	
}
