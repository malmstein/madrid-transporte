package es.malmstein.madridtransporte.library.requests;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import es.malmstein.madridtransporte.library.model.MTConstants;

public class Request {
	
	private String icClient = "WEB.PORTALMOVIL.ANDROID";
	private String passKey = "C2653B75-EF8C-48B1-A99F-79C62D0C0D61";
	
	private String requestPath;
	private List<NameValuePair> requestParameters;
	
	public String getRequestPath() {
		return requestPath;
	}
	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}
	
	public List<NameValuePair> getRequestParameters() {
		return requestParameters;
	}
	
	public void setRequestParameters(List<NameValuePair> callParameters) throws Exception {
		
		try {
			
			List<NameValuePair> localArrayList = new ArrayList<NameValuePair>(2);
		    localArrayList.add(new BasicNameValuePair(MTConstants.API_REQUEST_OBJECT.ID_CLIENT, icClient));
		    localArrayList.add(new BasicNameValuePair(MTConstants.API_REQUEST_OBJECT.PASS_KEY, passKey));
		    
		    for (NameValuePair pair : callParameters){
		    	localArrayList.add(pair);
		    }
		    
		    this.requestParameters = localArrayList;
					
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
}
