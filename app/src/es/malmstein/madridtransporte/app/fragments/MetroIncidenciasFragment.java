package es.malmstein.madridtransporte.app.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.actionbarsherlock.app.SherlockListFragment;

import es.malmstein.madridtransporte.app.R;
import es.malmstein.madridtransporte.app.adapters.MetroIncidenciasListAdapter;
import es.malmstein.madridtransporte.library.model.MTConstants;
import es.malmstein.madridtransporte.library.model.MTEngine;
import es.malmstein.madridtransporte.library.objects.MetroNews;

public class MetroIncidenciasFragment extends SherlockListFragment{

	private ViewSwitcher switcher;
	
    public static MetroIncidenciasFragment newInstance() {
    	MetroIncidenciasFragment f = new MetroIncidenciasFragment();
        return f;
    }  
    
    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_incidencias, container, false);
        
        switcher = (ViewSwitcher) v.findViewById(R.id.layout_incidencias_switcher);
               
        return v;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {   
    	super.onActivityCreated(savedInstanceState);
    	
    	if (MTEngine.getInstance().hasLocalMetroIncidencias() == true){
    		setListAdapter(new MetroIncidenciasListAdapter(getActivity(), R.layout.view_news_cell, MTEngine.getInstance().getLocalMetroIncidencias()));
    		switcher.showNext();
    	}else{
    		getActivity().getSupportLoaderManager().restartLoader(MTConstants.LOADER.EMT_INCIDENCIAS, null, emtIncidenciasLoader);    		
    	}
			
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("FragmentList", "Item clicked: " + id);
    }
    
	LoaderCallbacks<ArrayList<MetroNews>> emtIncidenciasLoader = new LoaderCallbacks<ArrayList<MetroNews>>() {

		@Override
		public Loader<ArrayList<MetroNews>> onCreateLoader(int paramInt, Bundle paramBundle) {
			return new AsyncTaskLoader<ArrayList<MetroNews>>(getActivity()) {

				protected void onStartLoading() {					
					this.forceLoad();
				}

				protected void onStopLoading() {
				
				}

				@Override
				public ArrayList<MetroNews> loadInBackground() {
					ArrayList<MetroNews> response = null;
					
					response = MTEngine.getInstance().parseMetroIncidencias();
					return response;
				}

			};
		}

		@Override
		public void onLoadFinished(Loader<ArrayList<MetroNews>> paramLoader, ArrayList<MetroNews> response) {
			paramLoader.abandon();		

    		setListAdapter(new MetroIncidenciasListAdapter(getActivity(), R.layout.view_news_cell, MTEngine.getInstance().getLocalMetroIncidencias()));
    		switcher.showNext();
		}

		@Override
		public void onLoaderReset(Loader<ArrayList<MetroNews>> arg0) {

		}
	};
    
}