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
import es.malmstein.madridtransporte.app.adapters.BusIncidenciasListAdapter;
import es.malmstein.madridtransporte.library.model.MTConstants;
import es.malmstein.madridtransporte.library.model.MTEngine;
import es.malmstein.madridtransporte.library.objects.EMTNews;

public class BusIncidenciasFragment extends SherlockListFragment{

	private ViewSwitcher switcher;
	
    public static BusIncidenciasFragment newInstance() {
    	BusIncidenciasFragment f = new BusIncidenciasFragment();
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
    	
    	if (MTEngine.getInstance().hasLocalEMTIncidencias() == true){
    		setListAdapter(new BusIncidenciasListAdapter(getActivity(), R.layout.view_news_cell, MTEngine.getInstance().getLocalEMTIncidencias()));
    		switcher.showNext();
    	}else{
    		getActivity().getSupportLoaderManager().restartLoader(MTConstants.LOADER.EMT_INCIDENCIAS, null, emtIncidenciasLoader);    		
    	}
			
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("FragmentList", "Item clicked: " + id);
    }
    
	LoaderCallbacks<ArrayList<EMTNews>> emtIncidenciasLoader = new LoaderCallbacks<ArrayList<EMTNews>>() {

		@Override
		public Loader<ArrayList<EMTNews>> onCreateLoader(int paramInt, Bundle paramBundle) {
			return new AsyncTaskLoader<ArrayList<EMTNews>>(getActivity()) {

				protected void onStartLoading() {					
					this.forceLoad();
				}

				protected void onStopLoading() {
				
				}

				@Override
				public ArrayList<EMTNews> loadInBackground() {
					ArrayList<EMTNews> response = null;
					
					response = MTEngine.getInstance().parseEMTIncidencias();
					return response;
				}

			};
		}

		@Override
		public void onLoadFinished(Loader<ArrayList<EMTNews>> paramLoader, ArrayList<EMTNews> response) {
			paramLoader.abandon();		

			setListAdapter(new BusIncidenciasListAdapter(getActivity(), R.layout.view_news_cell, response));			
			switcher.showNext();
		}

		@Override
		public void onLoaderReset(Loader<ArrayList<EMTNews>> arg0) {

		}
	};
    
}