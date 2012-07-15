package tasks;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import es.malmstein.madridtransporte.app.ui.MainActivity;
import es.malmstein.madridtransporte.library.model.MTEngine;
import es.malmstein.madridtransporte.library.objects.MetroLine;
import es.malmstein.madridtransporte.library.parsers.MetroParser;

public class ParseMetroDataAsyncTask extends AsyncTask<Void, Void, ArrayList<MetroLine>> implements Serializable{
	
    private static final long serialVersionUID = 3456364412779773035L;
    private FragmentActivity ctx;
     
    public ParseMetroDataAsyncTask(FragmentActivity paramActivityBase) {
    	this.ctx = paramActivityBase;
    }

	@Override
	protected ArrayList<MetroLine> doInBackground(Void... params) {
		
		ArrayList<MetroLine> response = new ArrayList<MetroLine>();
    	
        try {
        	MetroParser localMetroParser = new MetroParser();
        	response = localMetroParser.parse(ctx);
        	return response;	
        }catch (Exception e){
        	return null;
        }				
	}
	
    protected void onPostExecute(ArrayList<MetroLine> response) {
    	
    	if (response != null){
    		MTEngine.getInstance().setLocalMetroLines(response);
    	}    	    
    	
		Intent mainIntent = new Intent(ctx, MainActivity.class);
		this.ctx.startActivity(mainIntent);
		this.ctx.finish();
    	
    }
    
    public void relink(FragmentActivity paramActivityBase) {
    	this.ctx = paramActivityBase;
    }

    public void unlink() {
    	this.ctx = null;
    }
    
}