package es.malmstein.madridtransporte.library.requests;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import es.malmstein.madridtransporte.library.model.Constants;

public class GetStopsFromXYRequest extends Request{

    // Object Names for the API Calls
    public static class REQUEST_OBJECT {
        public final static String COORDINATE_X = "coordinateX";
        public final static String COORDINATE_Y = "coordinateY";
        public final static String RADIUS = "Radius";
        public final static String STATISTICS = "statistics";
        public final static String CULTURE_INFO = "cultureInfo";
    }
    
    public GetStopsFromXYRequest(String coordX, String coordY, String radius, String statistics, String culture){
    	
    	this.setRequestPath(Constants.getApiResourcePath(Constants.API_METHOD.GetStopsFromXYURL));
    	
		List<NameValuePair> localArrayList = new ArrayList<NameValuePair>(2);
	    localArrayList.add(new BasicNameValuePair(REQUEST_OBJECT.COORDINATE_X, coordX));
	    localArrayList.add(new BasicNameValuePair(REQUEST_OBJECT.COORDINATE_Y, coordY));
	    localArrayList.add(new BasicNameValuePair(REQUEST_OBJECT.RADIUS, radius));
	    localArrayList.add(new BasicNameValuePair(REQUEST_OBJECT.STATISTICS, statistics));
	    localArrayList.add(new BasicNameValuePair(REQUEST_OBJECT.CULTURE_INFO, culture));
	    
	    try {
			this.setRequestParameters(localArrayList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
