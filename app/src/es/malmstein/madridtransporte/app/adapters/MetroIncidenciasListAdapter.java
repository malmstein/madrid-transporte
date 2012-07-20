package es.malmstein.madridtransporte.app.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import es.malmstein.madridtransport.library.R;
import es.malmstein.madridtransporte.library.objects.MetroNews;

public class MetroIncidenciasListAdapter extends ArrayAdapter<MetroNews>{
    
    private LayoutInflater mInflater;
    private ArrayList<MetroNews> items;

    public MetroIncidenciasListAdapter(Context context, int textViewResourceId, ArrayList<MetroNews> items) {
        super(context, textViewResourceId, items);              
        
        mInflater = LayoutInflater.from(context);
        this.items = items;
    }  
    
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MetroNews getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
    	NewsViewHolder viewHolder;    	 
        
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.view_news_cell, null);
            
            viewHolder = new NewsViewHolder();
            
            viewHolder.newsTitle = (TextView) convertView.findViewById(R.id.tv_news_cell_title);                
            viewHolder.newsDate = (TextView) convertView.findViewById(R.id.tv_news_cell_pub_date);               
            
            convertView.setTag(viewHolder);
        }else{
        	viewHolder = (NewsViewHolder) convertView.getTag();
        }

        final MetroNews news = items.get(position);
        if (news != null){
        	viewHolder.newsTitle.setText(news.getTitle());        	
        	viewHolder.newsDate.setText(news.getFecha());
        }
        
        return convertView;
    }       
    
    static class NewsViewHolder {
    	TextView newsTitle;
    	TextView newsDate;
    }

     
}