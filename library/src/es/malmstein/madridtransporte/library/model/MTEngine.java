package es.malmstein.madridtransporte.library.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.xml.sax.InputSource;

import android.content.Context;
import android.text.TextUtils;

import com.bugsense.trace.BugSenseHandler;

import es.malmstein.madridtransporte.library.objects.Stop;
import es.malmstein.madridtransporte.library.parsers.XMLStopsReader;
import es.malmstein.madridtransporte.library.requests.GetStopsFromXYRequest;
import es.malmstein.madridtransporte.library.requests.Request;
import es.malmstein.madridtransporte.library.response.GetStopsFromXYResponse;
import es.malmstein.madridtransporte.library.utils.EasySSLSocketFactory;
import es.malmstein.madridtransporte.library.utils.LibraryUtils;

public class MTEngine {

	private static MTEngine instance;
	private Context context;
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	private MTEngine(Context cxt){
		this.setContext(cxt);
	}

	public static void startInstance(Context ctx) {
		if (instance == null) {
			instance = new MTEngine(ctx);
		}
		
		BugSenseHandler.setup(ctx, MTConstants.BUGSENSE_API);
	}

	public static void stopInstance() {
		instance = null;
	}

	public static MTEngine getInstance() {
		return instance;
	}
	
	public GetStopsFromXYResponse getStopsFromXY(GetStopsFromXYRequest request){
		GetStopsFromXYResponse response = new GetStopsFromXYResponse();		
		InputSource source;		
		try{
			 source = secureRequest(request);
			 List<Stop> localList2 = new XMLStopsReader().parse(source);
			 response.setData(localList2);
		}catch (Exception e){
			response.setData(null);
		}
		
		return response;
	}
	
	/**
	 * Handles a secure server request through https
	 * @param request request
	 * @return JSONObject response
	 * @throws BHException
	 */
	public InputSource secureRequest(Request request) throws Exception{
		
		if(!LibraryUtils.isInternetConnected(this.getContext())){
			throw new Exception("Internet Error");
		}
		
		URL url = null;

		try {
			url = new URL(request.getRequestPath());				
		}
		catch (MalformedURLException e) {
		    BugSenseHandler.log(MTConstants.BUGSENSE_LIB_TAG, e);
			throw new Exception( e.toString()+" AT REQUEST SERVER METHOD IN "+ MTEngine.class.toString());
		}
		
		DefaultHttpClient httpclient = getSecureHttpClient();
		
		// Prepare a request object
		HttpRequestBase httpReq;
		// Execute the request
		HttpResponse response;
		
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs = request.getRequestParameters();                                    
		
		try{
			httpReq= new HttpPost(url.toString());
			((HttpPost)httpReq).setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
			
			response = httpclient.execute(httpReq);
			HttpEntity entity = response.getEntity();
			
			if (entity != null)
			{
				// A Simple JSON Response Read
				String str = convertStreamToString(entity.getContent());
				
				if (!TextUtils.isEmpty(str)){
					return new InputSource(new StringReader(str));		
				}else{
					return null;
				}			 				
								
			}
		}
		catch (ClientProtocolException e)
		{
		    BugSenseHandler.log(MTConstants.BUGSENSE_LIB_TAG, e);
			throw new Exception(e.toString()+" AT REQUEST SERVER MEHTOD IN "+MTEngine.class.toString());
		}
		catch (IOException e)
		{
		    BugSenseHandler.log(MTConstants.BUGSENSE_LIB_TAG, e);
			throw new Exception(e.toString()+" AT REQUEST SERVER MEHTOD IN "+MTEngine.class.toString());
		}
		
		return null;
	}
	
	/**
	 * Constructor of the secure HTTP client
	 * @return
	 */
	public static DefaultHttpClient getSecureHttpClient()
	{
		try
		{
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new EasySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, "UTF-8");

			// Set the timeout in milliseconds until a connection is established.
			int timeoutConnection = 120000;
			HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
			// Set the default socket timeout (SO_TIMEOUT) 
			// in milliseconds which is the timeout for waiting for data.
			int timeoutSocket = 120000;
			HttpConnectionParams.setSoTimeout(params, timeoutSocket);
			
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

			return new DefaultHttpClient(ccm, params);
		}
		catch (Exception e)
		{
		    BugSenseHandler.log(MTConstants.BUGSENSE_LIB_TAG, e);
			return new DefaultHttpClient();
		}
	}
	
	/**
	 * Conversion between stream and string
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is)
	{		
        try {            
            final char[] buffer = new char[0x10000];
            StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(is, "UTF-8");
            int read;
            do {
              read = in.read(buffer, 0, buffer.length);
              if (read>0) {
                out.append(buffer, 0, read);
              }
            } while (read>=0);
            
            return out.toString();        
	    } catch (Exception e1) {
	        BugSenseHandler.log(MTConstants.BUGSENSE_LIB_TAG, e1);
	        e1.printStackTrace();
	    }
        
        return null;
	}
	
}
