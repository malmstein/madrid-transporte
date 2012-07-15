package es.malmstein.madridtransporte.library.parsers;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.content.Context;
import es.malmstein.madridtransporte.library.objects.MetroLine;

public class MetroParser{
	
	ArrayList<MetroLine> lineas = new ArrayList<MetroLine>();

	public ArrayList<MetroLine> parse(Context paramContext) {
	  
		SAXParserFactory localSAXParserFactory = SAXParserFactory.newInstance();
    
		try{
			SAXParser localSAXParser = localSAXParserFactory.newSAXParser();
			MetroParserHandler localRssHandler = new MetroParserHandler(this.lineas, paramContext);
			localSAXParser.parse(paramContext.getResources().getAssets().open("metro_lineas.xml"), localRssHandler);      
			return this.lineas;
		} catch (Exception localException) {
			throw new RuntimeException(localException);
		}    
	}
}