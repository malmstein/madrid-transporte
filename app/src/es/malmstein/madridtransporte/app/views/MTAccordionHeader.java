package es.malmstein.madridtransporte.app.views;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import es.malmstein.madridtransporte.app.R;
import es.malmstein.madridtransporte.library.model.MTEngine;
import es.malmstein.madridtransporte.library.objects.MetroLine;
import es.malmstein.madridtransporte.library.objects.MetroStation;

public class MTAccordionHeader extends LinearLayout implements OnClickListener {
	
	private LinearLayout mPanel;
	private TextView mTitle;	
	
	private BHAccordionHideListener mListener;
	
	private FragmentActivity mActivity;
	private MetroLine mMetroLine;
	
	public static interface BHAccordionHideListener{
		void onAccordionOpened(MTAccordionHeader v);
	}	

	public MTAccordionHeader(Context context) {
		super(context);
		init(context);
	}

	public MTAccordionHeader(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context)
	{			
		LayoutInflater.from(context).inflate(R.layout.view_accordion_header_cell, this, true);
		mTitle = (TextView) this.findViewById(R.id.header_name);	
		mPanel = (LinearLayout) this.findViewById(R.id.header_panel);
		
		this.setOnClickListener(this);	
	}
	
	/**
	 * Set the city group name and the child cities
	 * @param title
	 */
	public void setMetroGroup(MetroLine line, SherlockFragment activity, int i){
		
		this.setTag(i);
		
		mMetroLine = line;
		mListener = (BHAccordionHideListener) activity;
		mActivity = activity.getActivity();
		
		mTitle.setText(line.getNombre());  
		    
		mPanel.removeAllViews();
		        
        ArrayList<MetroStation> childStations = line.getEstaciones();
        for (MetroStation station : childStations){
            MTAccordionItem vi = new MTAccordionItem(mActivity);
            vi.setMetroStation(station, mActivity);
            mPanel.addView(vi);        
		}
	}

	@Override
	public void onClick(View v) {
		if (!isEnabled())
			return;
		
	    // We hide or show the panel
        int panelVisibility;
        panelVisibility = mPanel.getVisibility();
        if(panelVisibility != View.VISIBLE){
            mPanel.setVisibility(View.VISIBLE);
        }
                
        hide();
        
        if(panelVisibility != View.VISIBLE) {
            show();
        }	
		
	}
	
	/**
	 * Hide the accordion
	 */
	public void hide(){
		mPanel.startAnimation(new ScaleAnimToHide(1.0f, 1.0f, 1.0f, 0.0f, 500, mPanel, true));		
	}
	
	/**
	 * Show the accordion
	 */
	public void show(){
		mPanel.startAnimation(new ScaleAnimToShow(1.0f, 1.0f, 1.0f, 0.0f, 500, mPanel, true));
		mListener.onAccordionOpened(this);		
	}
	
	public class ScaleAnimToHide extends ScaleAnimation	{

	       private View mView;
	       private LayoutParams mLayoutParams;
	       private int mMarginBottomFromY, mMarginBottomToY;
	       private boolean mVanishAfter = false;

	       public ScaleAnimToHide(float fromX, float toX, float fromY, float toY, int duration, View view,boolean vanishAfter) {
	           super(fromX, toX, fromY, toY);
	           setDuration(duration);
	           mView = view;
	           mVanishAfter = vanishAfter;
	           mLayoutParams = (LayoutParams) view.getLayoutParams();
	           int height = mView.getHeight();
	           mMarginBottomFromY = (int) (height * fromY) + mLayoutParams.bottomMargin - height;
	           mMarginBottomToY = (int) (0 - ((height * toY) + mLayoutParams.bottomMargin)) - height;	         	          
	       }

	       @Override
	       protected void applyTransformation(float interpolatedTime, Transformation t) {
	           super.applyTransformation(interpolatedTime, t);
	           if (interpolatedTime < 1.0f)
	           {
	               int newMarginBottom = mMarginBottomFromY + (int) ((mMarginBottomToY - mMarginBottomFromY) * interpolatedTime);
	               mLayoutParams.setMargins(mLayoutParams.leftMargin, mLayoutParams.topMargin,mLayoutParams.rightMargin, newMarginBottom);
	               mView.getParent().requestLayout();	               
	           }
	           else if (mVanishAfter)
	           {
	               mView.setVisibility(View.GONE);
	           }
	       }
	}
	
	public class ScaleAnimToShow extends ScaleAnimation	{

	       private View mView;
	       private LayoutParams mLayoutParams;
	       private int mMarginBottomFromY, mMarginBottomToY;

	       public ScaleAnimToShow(float toX, float fromX, float toY, float fromY, int duration, View view,boolean vanishAfter)
	       {
	           super(fromX, toX, fromY, toY);
	           setDuration(duration);
	           mView = view;	           
	           mLayoutParams = (LayoutParams) view.getLayoutParams();
	           mView.setVisibility(View.VISIBLE);
	           int height = mView.getHeight();

	           mMarginBottomFromY = 0;
	           mMarginBottomToY = height;	         	           
	       }

	       @Override
	       protected void applyTransformation(float interpolatedTime, Transformation t)
	       {
	           super.applyTransformation(interpolatedTime, t);
	           if (interpolatedTime < 1.0f)
	           {
	               int newMarginBottom = (int) ((mMarginBottomToY - mMarginBottomFromY) * interpolatedTime) - mMarginBottomToY;
	               mLayoutParams.setMargins(mLayoutParams.leftMargin, mLayoutParams.topMargin,mLayoutParams.rightMargin, newMarginBottom);
	               mView.getParent().requestLayout();	               
	           }
	       }
	}
	
}
