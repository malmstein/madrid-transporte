package es.malmstein.madridtransporte.app.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import es.malmstein.madridtransporte.app.R;
import es.malmstein.madridtransporte.library.objects.MetroCorrespondence;
import es.malmstein.madridtransporte.library.objects.MetroStation;

public class ExpandableMetroLineListAdapter extends ArrayAdapter<MetroStation> {        
   
	private LayoutInflater mInflater;
    private ArrayList<MetroStation> mLines;

    public ExpandableMetroLineListAdapter(Context context, int textViewResourceId, ArrayList<MetroStation> items) {
    	super(context, textViewResourceId, items);     	    	
        this.mLines = items;        
        mInflater = LayoutInflater.from(context);
    }
    
    public int getItemViewType(int paramInt) {   
    	
    	int itemType = 0;
    	MetroStation item = (MetroStation) mLines.get(paramInt);
    	
    	if (item.titulo == true){
    		itemType =  0;
    	}else{
    		itemType = 1;
    	}
    	
    	return itemType;
    }
    
    public ArrayList<MetroStation> filter(String paramString) {
      ArrayList<MetroStation> localArrayList = new ArrayList<MetroStation>();
      
      for (MetroStation localStation : this.mLines){
    	  if (localStation.getNombre().contains(paramString))
    		  localArrayList.add(localStation);
      }
      
      return localArrayList;      
    }
        
    // Return a child view. You can load your custom layout here.
    @Override
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup){
    	                                                   
    	MetroLineViewHolder linesHolder; 
    	MetroStationViewHolder stationsHolder; 
    	
    	MetroStation estacion = (MetroStation) mLines.get(paramInt);
        
    	// Titulo o estacion
    	if (getItemViewType(paramInt) == 0){
    		if (paramView == null) {
            	paramView = mInflater.inflate(R.layout.view_line_row, null);            
            	linesHolder = new MetroLineViewHolder();            
            	linesHolder.lineTitle = (TextView) paramView.findViewById(R.id.view_line_row_name);     
            	linesHolder.lineImage = (ImageView) paramView.findViewById(R.id.view_line_row_image);             
            	paramView.setTag(linesHolder);
    		}else{
    			linesHolder = (MetroLineViewHolder) paramView.getTag();
    		}
    		
    		linesHolder.lineTitle.setText(estacion.getNombre());
    		linesHolder.lineImage.setImageResource(estacion.getId());
    		
    		return paramView;
    	}else{
    		if (paramView == null) {
            	paramView = mInflater.inflate(R.layout.view_station_row, null);            
            	stationsHolder = new MetroStationViewHolder();            
            	stationsHolder.stationTitle = (TextView) paramView.findViewById(R.id.view_station_name);     
            	stationsHolder.stationCorrespondencias = (LinearLayout) paramView.findViewById(R.id.view_station_layout);             
            	paramView.setTag(stationsHolder);	
            }else{
            	stationsHolder = (MetroStationViewHolder) paramView.getTag();
            }
    		
    		stationsHolder.stationTitle.setText(estacion.getNombre());
    		stationsHolder.stationCorrespondencias.removeAllViews();
    		
        	if (estacion.getCorrespondencias().size() > 1){
        		for (MetroCorrespondence cor : estacion.getCorrespondencias()){
        	        ImageView localImageView = new ImageView(paramView.getContext());
        	        localImageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        	        localImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        	        localImageView.setPadding(3, 0, 3, 0);
        	        localImageView.setImageResource(cor.getId());
        	        stationsHolder.stationCorrespondencias.addView(localImageView);
        		}
        	}
        	
        	return paramView;            
    	}
        
    }
    
    public int getViewTypeCount() {
      return 2;
    }

	static class MetroStationViewHolder {
		TextView stationTitle;    	
		LinearLayout stationCorrespondencias;
	}
	
	static class MetroLineViewHolder {
		TextView lineTitle;     
	 	ImageView lineImage;
	}  
    
        
	
}