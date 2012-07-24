package es.malmstein.madridtransporte.app.ui;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnActionExpandListener;

import es.malmstein.madridtransporte.app.R;
import es.malmstein.madridtransporte.app.adapters.ExpandableMetroLineListAdapter;
import es.malmstein.madridtransporte.app.utils.MTActivity;
import es.malmstein.madridtransporte.library.model.MTEngine;
import es.malmstein.madridtransporte.library.objects.MetroLine;
import es.malmstein.madridtransporte.library.objects.MetroStation;

public class InfoMetro extends MTActivity implements ActionBar.OnNavigationListener {

	private ExpandableMetroLineListAdapter metroLinesAdapter;        
    private ListView metroListView;
    
    private ArrayList<MetroLine> mLines;
    
    private DropDownCustomAdapter dropDownAdapter;
    
    private EditText et_search;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(R.layout.activity_info_metro);
		
		metroListView = (ListView) this.findViewById(R.id.infoMetroListView);				
				        
        dropDownAdapter = new DropDownCustomAdapter(this, R.layout.view_line_row, MTEngine.getInstance().getLocalMetroLines());
        
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);        
        getSupportActionBar().setListNavigationCallbacks(dropDownAdapter, this);
		
        mLines = MTEngine.getInstance().getLocalMetroLines();
		metroLinesAdapter = new ExpandableMetroLineListAdapter(this, R.layout.view_line_dropdown_row, cargarEstaciones(mLines)); 
		metroListView.setAdapter(metroLinesAdapter);    
		
	}	
	
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
    	
    	if (itemPosition == 0){
    		metroLinesAdapter = new ExpandableMetroLineListAdapter(this, R.layout.view_line_dropdown_row, cargarEstaciones(mLines));
    	}else{
    		metroLinesAdapter = new ExpandableMetroLineListAdapter(this, R.layout.view_line_dropdown_row, cargarEstaciones(mLines.get(itemPosition)));
    	}
		 
		metroListView.setAdapter(metroLinesAdapter);    
    	
        return true;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(getString(R.string.menu_title_search))
            .setIcon(R.drawable.ic_action_search)
            .setActionView(R.layout.collapsible_edittext)
            .setOnActionExpandListener(new OnActionExpandListener() {
                
                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    et_search = (EditText) item.getActionView();
                    et_search.post(new Runnable() {
                        @Override
                        public void run() {
                            et_search.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(et_search, InputMethodManager.SHOW_IMPLICIT);
                        }
                    });

                    et_search.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void afterTextChanged(Editable s) {
//                       		metroLinesAdapter = new ExpandableMetroLineListAdapter(InfoMetro.this, R.layout.view_line_dropdown_row, metroLinesAdapter.filter(s.toString()));
//                       		metroListView.setAdapter(metroLinesAdapter); 
                        }

                        @Override
                        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {}    
                    });
                    
                    et_search.setOnEditorActionListener(new OnEditorActionListener() {                       
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            if (actionId == EditorInfo.IME_ACTION_SEARCH)
                            {
                                return true;
                            }
                            return false;
                        }
                    });
                    return true;
                }
                
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
                    et_search.post(new Runnable() {
                        public void run() {
                            et_search.clearFocus();
                        }
                    }); 
                    return true;
                }
            })
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);        
      
        return true;
    }
	
	private ArrayList<MetroStation> cargarEstaciones(ArrayList<MetroLine> lineas){
		
		ArrayList<MetroStation> localStation = new ArrayList<MetroStation>();
		
		for (MetroLine line : lineas){
			for (int i = 0; i < line.getEstaciones().size(); i++){
				MetroStation station = line.getEstaciones().get(i);
				if (i == 0){					
					MetroStation headerStation = new MetroStation();
					headerStation.titulo = true;
					headerStation.setNombre(line.getNombre());
					headerStation.setId(line.getImagen());
					localStation.add(headerStation);
					localStation.add(station);
				}else{
					station.titulo = false;
					localStation.add(station);
				}								
			}			
		}		
		return localStation;
	}
	
	private ArrayList<MetroStation> cargarEstaciones(MetroLine line){
		
		ArrayList<MetroStation> localStation = new ArrayList<MetroStation>();
		
		for (int i = 0; i < line.getEstaciones().size(); i++){
			MetroStation station = line.getEstaciones().get(i);
			if (i == 0){					
				MetroStation headerStation = new MetroStation();
				headerStation.titulo = true;
				headerStation.setNombre(line.getNombre());
				headerStation.setId(line.getImagen());
				localStation.add(headerStation);
				localStation.add(station);
			}else{
				station.titulo = false;
				localStation.add(station);
			}								
		}	
		
		return localStation;
	}
	
	public class DropDownCustomAdapter extends ArrayAdapter<MetroLine> {   
		
		private LayoutInflater mInflater;
		private ArrayList<MetroLine> mLines;
		
	    public DropDownCustomAdapter(Context context, int textViewResourceId, ArrayList<MetroLine> items) {
	    	super(context, textViewResourceId, items);     	    		    
	    	mLines = items;
	    	
	    	MetroLine allMetroLines = new MetroLine();	    	
	    	allMetroLines.setNombre("Todos las lineas");
	    	allMetroLines.setImagen(R.drawable.l10);
	    	
	    	items.add(0, allMetroLines);
	        mInflater = LayoutInflater.from(context);
	    }
	    
	    // Return a child view. You can load your custom layout here.
	    @Override
	    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup){
	    	
	    	return initView(paramInt, paramView);
	    }
	    
	    @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return initView(position, convertView);
        }

        private View initView(int position, View convertView) {

	    	MetroLineViewHolder linesHolder; 
	    	
	    	MetroLine line = (MetroLine) mLines.get(position);
	    	
    		if (convertView == null) {
    			convertView = mInflater.inflate(R.layout.view_line_dropdown_row, null);            
            	linesHolder = new MetroLineViewHolder();            
            	linesHolder.lineOrigen = (TextView) convertView.findViewById(R.id.view_line_dropdown_origen);     
            	linesHolder.lineDestino = (TextView) convertView.findViewById(R.id.view_line_dropdown_destino);     
            	linesHolder.lineImage = (ImageView) convertView.findViewById(R.id.view_line_dropdown_image);             
            	convertView.setTag(linesHolder);
    		}else{
    			linesHolder = (MetroLineViewHolder) convertView.getTag();
    		}
    		
    		String linea = line.getNombre();
    		String[] aux = linea.split(" - ");
    		
    		if (aux.length > 1){
        		linesHolder.lineOrigen.setText(aux[0]);
        		linesHolder.lineDestino.setText(aux[1]);
        		linesHolder.lineImage.setImageResource(line.getImagen());	        		
    		}else{
        		linesHolder.lineOrigen.setText(aux[0]);
        		linesHolder.lineDestino.setVisibility(View.GONE);
        		linesHolder.lineImage.setImageResource(line.getImagen());	
    		}
    		
    		return convertView;
    		
        }
	    
		class MetroLineViewHolder {
			TextView lineOrigen;
			TextView lineDestino;
		 	ImageView lineImage;
		}  
	    
	}
}
