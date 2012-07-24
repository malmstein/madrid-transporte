package es.malmstein.madridtransporte.app.views;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import es.malmstein.madridtransporte.app.R;
import es.malmstein.madridtransporte.library.objects.MetroLine;
import es.malmstein.madridtransporte.library.objects.MetroStation;

public class MTAccordionItem extends RelativeLayout implements OnClickListener {
    
    private TextView mTitle;
    private MetroStation mMetroStation;
    private FragmentActivity mActivity;
    
    public MTAccordionItem(Context context) {
        super(context);
        init(context);
    }

    public MTAccordionItem(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);      
    }
    
    @Override
    public void onClick(View v) {
        if (!isEnabled())
            return;
        
//        BHApp.getInstance().setCurrentCity(mCity);
//        Intent hotelDealsIntent = new Intent(mActivity, HotelDealsActivity.class);      
//        mActivity.startActivity(hotelDealsIntent);    
    }
    
    private void init(Context context){
        
        LayoutInflater.from(context).inflate(R.layout.view_accordion_item_cell, this, true);
        
        mTitle = (TextView) this.findViewById(R.id.accordion_item_name);
        
        this.setOnClickListener(this);      
    }
    
    public void setMetroStation(MetroStation station, FragmentActivity activity){
        this.mMetroStation = station;
        this.mActivity = activity;
        
        mTitle.setText(station.getNombre());   
    }    

}
